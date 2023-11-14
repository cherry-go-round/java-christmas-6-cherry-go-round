package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrdersTest {
    @DisplayName("중복 메뉴를 입력할 시 예외를 반환한다.")
    @Test
    void throw_Exception_If_Duplicated_Order() {
        Order first = new Order(Menu.CHOCOLATE_CAKE, 1);
        Order second = new Order(Menu.CHOCOLATE_CAKE, 2);
        assertThatThrownBy(() -> new Orders(List.of(first, second))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴를 20개 초과 주문할 시 예외를 반환한다.")
    @Test
    void throw_Exception_If_Order_Over_20() {
        Order first = new Order(Menu.T_BONE_STEAK, 3);
        Order second = new Order(Menu.BARBECUE_RIP, 3);
        Order third = new Order(Menu.SEAFOOD_PASTA, 4);
        Order fourth = new Order(Menu.CHRISTMAS_PASTA, 4);
        Order fifth = new Order(Menu.ICE_CREAM, 4);
        Order sixth = new Order(Menu.RED_WINE, 3);
        //total: 21
        assertThatThrownBy(() -> new Orders(List.of(first, second, third, fourth, fifth, sixth)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문할 시 예외를 반환한다.")
    @Test
    void throw_Exception_If_Only_Beverage() {
        Order first = new Order(Menu.CHAMPAGNE, 2);
        assertThatThrownBy(() -> new Orders(List.of(first)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
