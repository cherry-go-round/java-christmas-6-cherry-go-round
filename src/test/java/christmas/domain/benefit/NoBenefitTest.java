package christmas.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NoBenefitTest {
    @DisplayName("0원 할인")
    @Test
    void discount_1000() {
        //given
        NoBenefit noBenefit = new NoBenefit();

        //when
        BenefitDetail detail = noBenefit.detail();
        int amount = detail.amount();

        //then
        assertThat(amount).isEqualTo(0);
    }
}
