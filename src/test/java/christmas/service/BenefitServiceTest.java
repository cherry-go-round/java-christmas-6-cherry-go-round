package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import christmas.Menu;
import christmas.Order;
import christmas.Orders;
import christmas.domain.AllBenefits;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitServiceTest {
    @DisplayName("크리스마스 할인 예제")
    @Test
    void christmas_Discount_Test() {
        //given
        LocalDate christmas = LocalDate.of(2023, 12, 25);
        BenefitService benefitService = new BenefitService();

        //when
        Order order = new Order(Menu.CHOCOLATE_CAKE, 1);
        Orders orders = new Orders(List.of(order));
        AllBenefits details = benefitService.getDetails(christmas, orders);
        int totalDiscount = details.totalDiscount();

        //then
        //디데이 할인 3,400원 + 평일 할인 2,023원 + 특별 할인 1,000원 = 6,423원
        assertThat(totalDiscount).isEqualTo(6_423);
    }

    @DisplayName("주문 금액이 10,000원 미만인 경우 적용되지 않는다.")
    @Test
    void zero_Discount_If_Under_10_000() {
        //given
        LocalDate christmas = LocalDate.of(2023, 12, 25);
        BenefitService benefitService = new BenefitService();

        //when
        Order order = new Order(Menu.ICE_CREAM, 1);
        Orders orders = new Orders(List.of(order));
        AllBenefits details = benefitService.getDetails(christmas, orders);
        int totalDiscount = details.totalDiscount();

        //then
        assertThat(totalDiscount).isEqualTo(0);
    }

    @DisplayName("총주문 금액이 12만원 이상이면 증정한다.")
    @Test
    void when_Total_Amount_Is_Greater_Than_120000_Then_giveaway() {
        //given
        LocalDate christmas = LocalDate.of(2023, 12, 25);
        BenefitService benefitService = new BenefitService();

        //when
        //CHRISTMAS_PASTA: 25,000 * 6 = 150,000
        Order order = new Order(Menu.CHRISTMAS_PASTA, 6);
        Orders orders = new Orders(List.of(order));
        AllBenefits details = benefitService.getDetails(christmas, orders);

        //then
        assertThat(details.totalDiscount()).isNotEqualTo(details.totalBenefit());
    }

    @Test
    void getGiveawayComposition() {
        //given
        BenefitService benefitService = new BenefitService();

        //when
        //CHRISTMAS_PASTA: 25,000 * 6 = 150,000
        Order order = new Order(Menu.CHRISTMAS_PASTA, 6);
        Orders orders = new Orders(List.of(order));
        Map<String, Integer> giveawayComposition = benefitService.getGiveawayComposition(orders);

        //then
        assertThat(giveawayComposition)
                .contains(entry("샴페인", 1));
    }
}