package christmas.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WeekDayDiscountTest {
    @DisplayName("디저트 메뉴가 없으면 적용되지 않는다.")
    @Test
    void zero_Discount_Without_Dessert_Menu() {
        //given
        Order order = new Order(Menu.T_BONE_STEAK, 1);
        Orders orders = new Orders(List.of(order));
        WeekDayDiscount weekDayDiscount = new WeekDayDiscount(orders);

        //when
        BenefitDetail detail = weekDayDiscount.detail();
        int amount = detail.amount();

        //then
        assertThat(amount).isEqualTo(0);
    }

    @DisplayName("디저트 메뉴 한 개당 2023원이 할인된다.")
    @Test
    void discount_By_Dessert_Orders() {
        //given
        Order first = new Order(Menu.ICE_CREAM, 2);
        Order second = new Order(Menu.CHOCOLATE_CAKE, 1);
        Orders orders = new Orders(List.of(first, second));
        WeekDayDiscount weekDayDiscount = new WeekDayDiscount(orders);

        //when
        BenefitDetail detail = weekDayDiscount.detail();
        int amount = detail.amount();

        //then
        assertThat(amount).isEqualTo(2023 * 3);
    }
}
