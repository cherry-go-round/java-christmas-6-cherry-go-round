package christmas.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.BenefitDetail;
import christmas.domain.benefit.NoDiscount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NoDiscountTest {
    @DisplayName("0원 할인")
    @Test
    void discount_1000() {
        //given
        NoDiscount noDiscount = new NoDiscount();

        //when
        BenefitDetail detail = noDiscount.detail();
        int amount = detail.amount();

        //then
        assertThat(amount).isEqualTo(0);
    }
}
