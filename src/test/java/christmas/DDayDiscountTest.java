package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class DDayDiscountTest {
    @DisplayName("26일 이후부터는 적용되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void constructor_ThrowsException_IfInvalidInput(int date) {
        assertThatThrownBy(() -> new DDayDiscount(date)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("날짜에 따른 할인 금액을 구한다.")
    @ParameterizedTest
    @CsvSource({
            "1,  1000", "2,  1100", "3,  1200", "4,  1300", "5,  1400",
            "6,  1500", "7,  1600", "8,  1700", "9,  1800", "10, 1900",
            "11, 2000", "12, 2100", "13, 2200", "14, 2300", "15, 2400",
            "16, 2500", "17, 2600", "18, 2700", "19, 2800", "20, 2900",
            "21, 3000", "22, 3100", "23, 3200", "24, 3300", "25, 3400"
    })
    void calculate_RightResult_ByDate(int date, int amount) {
        //given
        DDayDiscount dDayDiscount = new DDayDiscount(date);

        //when
        int result = dDayDiscount.discountByDate();

        //then
        assertThat(result).isEqualTo(amount);
    }
}
