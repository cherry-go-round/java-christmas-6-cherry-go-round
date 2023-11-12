package christmas.discount;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpecialDiscountTest {
    @DisplayName("1,000원 할인")
    @Test
    void discount_1000() {
        SpecialDiscount specialDiscount = new SpecialDiscount();
        int amount = specialDiscount.discount();
        assertThat(amount).isEqualTo(1_000);
    }
}
