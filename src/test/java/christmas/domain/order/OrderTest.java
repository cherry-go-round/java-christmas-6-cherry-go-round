package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @DisplayName("메뉴가 1개 미만이면 예외를 반환한다.")
    @Test
    void throw_Exception_When_Menu_Under_1() {
        assertThatThrownBy(() -> new Order(Menu.CHOCOLATE_CAKE, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}