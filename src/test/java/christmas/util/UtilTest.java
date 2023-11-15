package christmas.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UtilTest {
    @DisplayName("notEmpty 메소드 테스트")
    @Test
    void notEmptyTest() {
        String blank = "";
        ErrorMessage testMessage = ErrorMessage.INVALID_ORDER;
        assertThatThrownBy(() -> Util.notEmpty("", testMessage))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(testMessage.getMessage());
    }
}