package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import christmas.domain.benefit.Benefits;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReservationServiceTest {
    @DisplayName("크리스마스 할인 예제")
    @Test
    void christmas_Discount_Test() {
        //given
        LocalDate christmas = LocalDate.of(2023, 12, 25);
        Order order = new Order(Menu.CHOCOLATE_CAKE, 1);
        Orders orders = new Orders(List.of(order));
        ReservationService reservationService = new ReservationService(christmas, orders);

        //when
        Benefits benefits = reservationService.allBenefits();
        int totalDiscount = benefits.totalDiscount();

        //then
        //디데이 할인 3,400원 + 평일 할인 2,023원 + 특별 할인 1,000원 = 6,423원
        assertThat(totalDiscount).isEqualTo(6_423);
    }

    @DisplayName("주문 금액이 10,000원 미만인 경우 적용되지 않는다.")
    @Test
    void zero_Discount_If_Under_10_000() {
        //given
        LocalDate christmas = LocalDate.of(2023, 12, 25);
        Order order = new Order(Menu.ICE_CREAM, 1);
        Orders orders = new Orders(List.of(order));
        ReservationService reservationService = new ReservationService(christmas, orders);

        //when
        Benefits details = reservationService.allBenefits();
        int totalDiscount = details.totalDiscount();

        //then
        assertThat(totalDiscount).isEqualTo(0);
    }

    @DisplayName("총주문 금액이 12만원 이상이면 증정한다.")
    @Test
    void when_Total_Amount_Is_Greater_Than_120000_Then_giveaway() {
        //given
        LocalDate christmas = LocalDate.of(2023, 12, 25);
        Order order = new Order(Menu.CHRISTMAS_PASTA, 6);
        Orders orders = new Orders(List.of(order));
        ReservationService reservationService = new ReservationService(christmas, orders);

        //when
        //CHRISTMAS_PASTA: 25,000 * 6 = 150,000
        Benefits benefits = reservationService.allBenefits();

        //then
        assertThat(benefits.totalDiscount()).isNotEqualTo(benefits.totalBenefit());
    }

    @DisplayName("증정 메뉴를 알맞게 구한다.")
    @Test
    void getGiveawayCompositionTest() {
        //given
        LocalDate christmas = LocalDate.of(2023, 12, 25);
        Order order = new Order(Menu.CHRISTMAS_PASTA, 6);
        Orders orders = new Orders(List.of(order));
        ReservationService reservationService = new ReservationService(christmas, orders);

        //when
        //CHRISTMAS_PASTA: 25,000 * 6 = 150,000
        Map<String, Integer> giveawayComposition = reservationService.getGiveawayComposition();

        //then
        assertThat(giveawayComposition).contains(entry("샴페인", 1));
    }

    @DisplayName("크리스마스 이후에는 디데이 할인이 적용되지 않는다. 평일")
    @Test
    void no_D_Day_Discount_After_Christmas_weekday() {
        //given
        LocalDate christmas = LocalDate.of(2023, 12, 26);
        Order order = new Order(Menu.BARBECUE_RIP, 6);
        Orders orders = new Orders(List.of(order));
        ReservationService reservationService = new ReservationService(christmas, orders);

        //when
        //디데이 할인 없음 + 평일 할인(디저트) 미적용
        Benefits benefits = reservationService.allBenefits();
        int totalDiscount = benefits.totalDiscount();

        //then
        assertThat(totalDiscount).isEqualTo(0);
    }

    @DisplayName("크리스마스 이후에는 디데이 할인이 적용되지 않는다. 주말")
    @Test
    void no_D_Day_Discount_After_Christmas_weekend() {
        //given
        LocalDate christmas = LocalDate.of(2023, 12, 29);
        Order order = new Order(Menu.ICE_CREAM, 6);
        Orders orders = new Orders(List.of(order));
        ReservationService reservationService = new ReservationService(christmas, orders);

        //when
        //디데이 할인 없음 + 주말 할인(메인 메뉴) 미적용
        Benefits benefits = reservationService.allBenefits();
        int totalDiscount = benefits.totalDiscount();

        //then
        assertThat(totalDiscount).isEqualTo(0);
    }
}