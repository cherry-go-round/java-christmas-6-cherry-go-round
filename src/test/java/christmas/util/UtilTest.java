package christmas.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UtilTest {
    @DisplayName("notEmpty 메소드 테스트")
    @Test
    void notEmptyTest() {
        //given
        String blank = "";
        ErrorMessage testMessage = ErrorMessage.INVALID_ORDER;

        //when-then
        assertThatThrownBy(() -> Util.notEmpty(blank, testMessage))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(testMessage.getMessage());
    }
}