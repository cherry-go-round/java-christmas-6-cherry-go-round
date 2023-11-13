package christmas.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.BenefitDetail;
import christmas.domain.benefit.DDayDiscount;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DDayDiscountTest {
    @DisplayName("날짜에 따른 할인 금액을 구한다.")
    @ParameterizedTest
    @CsvSource({
            "1,  1000", "2,  1100", "3,  1200", "4,  1300", "5,  1400",
            "6,  1500", "7,  1600", "8,  1700", "9,  1800", "10, 1900",
            "11, 2000", "12, 2100", "13, 2200", "14, 2300", "15, 2400",
            "16, 2500", "17, 2600", "18, 2700", "19, 2800", "20, 2900",
            "21, 3000", "22, 3100", "23, 3200", "24, 3300", "25, 3400"
    })
    void calculate_RightResult_ByDate(int day, int amount) {
        //given
        LocalDate localDate = LocalDate.of(2023, 12, day);
        DDayDiscount dDayDiscount = new DDayDiscount(localDate);

        //when
        BenefitDetail detail = dDayDiscount.detail();
        int result = detail.amount();

        //then
        assertThat(result).isEqualTo(amount);
    }
}
