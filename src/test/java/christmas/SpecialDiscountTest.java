package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpecialDiscountTest {
    @DisplayName("이벤트 달력에 별이 있으면 1,000원 할인")
    @Test
    void when_StarDay_Then_Discount_1000() {
        LocalDate reservationDate = LocalDate.of(2023, 12, 25);
        SpecialDiscount specialDiscount = new SpecialDiscount();
        int amount = specialDiscount.discount(reservationDate);
        assertThat(amount).isEqualTo(1_000);
    }
}
