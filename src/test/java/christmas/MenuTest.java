package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {
    @DisplayName("메뉴 이름으로 메뉴를 찾는다.")
    @Test
    void findTestSuccess() {
        Menu tapas = Menu.find("타파스");
        assertThat(tapas).isEqualTo(Menu.TAPAS);
    }

    @DisplayName("없는 메뉴를 찾으면 예외를 반환한다.")
    @Test
    void throw_Exception_If_No_Such_Menu() {
        assertThatThrownBy(() -> Menu.find("감자튀김"))
                .isInstanceOf(NoSuchElementException.class);
    }
}