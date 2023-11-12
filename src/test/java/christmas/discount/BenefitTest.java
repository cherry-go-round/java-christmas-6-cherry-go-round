package christmas.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.Menu;
import christmas.Order;
import christmas.Orders;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitTest {
    @DisplayName("크리스마스 할인 예제")
    @Test
    void christmas_Discount_Test() {
        //given
        LocalDate christmas = LocalDate.of(2023, 12, 25);
        Benefit benefit = new Benefit(christmas);

        //when
        Order order = new Order(Menu.CHOCOLATE_CAKE, 1);
        Orders orders = new Orders(order);
        int totalDiscount = benefit.totalDiscount(orders);

        //then
        //디데이 할인 3,400원 + 평일 할인 2,023원 + 특별 할인 1,000원 = 6,423원
        assertThat(totalDiscount).isEqualTo(6_423);
    }

    @DisplayName("주문 금액이 10,000원 미만인 경우 적용되지 않는다.")
    @Test
    void zero_Discount_If_Under_10_000() {
        //given
        LocalDate christmas = LocalDate.of(2023, 12, 25);
        Benefit benefit = new Benefit(christmas);

        //when
        Order order = new Order(Menu.ICE_CREAM, 1);
        Orders orders = new Orders(order);
        int totalDiscount = benefit.totalDiscount(orders);

        //then
        assertThat(totalDiscount).isEqualTo(0);
    }
}