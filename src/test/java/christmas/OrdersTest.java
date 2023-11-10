package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrdersTest {
    @DisplayName("중복 메뉴를 입력할 시 예외를 반환한다.")
    @Test
    void throw_Exception_If_Duplicated_Order() {
        Order first = new Order(Menu.CHOCOLATE_CAKE, 1);
        Order second = new Order(Menu.CHOCOLATE_CAKE, 2);
        assertThatThrownBy(() -> new Orders(first, second)).isInstanceOf(IllegalArgumentException.class);
    }
}
