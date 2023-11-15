package christmas.domain.date;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventDateTest {
    @DisplayName("12월에 없는 날을 입력하면 예외를 반환한다.")
    @Test
    void throw_Exception_When_Not_December_Date() {
        assertThatThrownBy(() -> EventDate.convert(32))
                .isInstanceOf(IllegalArgumentException.class);
    }
}