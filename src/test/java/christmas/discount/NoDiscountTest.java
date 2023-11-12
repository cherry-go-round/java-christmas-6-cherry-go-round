package christmas.discount;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NoDiscountTest {
    @DisplayName("0원 할인")
    @Test
    void discount_1000() {
        NoDiscount noDiscount = new NoDiscount();
        int amount = noDiscount.discount();
        assertThat(amount).isEqualTo(0);
    }
}
