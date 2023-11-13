package christmas.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.BenefitDetail;
import christmas.domain.benefit.SpecialDiscount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpecialDiscountTest {
    @DisplayName("1,000원 할인")
    @Test
    void discount_1000() {
        //given
        SpecialDiscount specialDiscount = new SpecialDiscount();

        //when
        BenefitDetail detail = specialDiscount.detail();
        int amount = detail.amount();

        //then
        assertThat(amount).isEqualTo(1_000);
    }
}
