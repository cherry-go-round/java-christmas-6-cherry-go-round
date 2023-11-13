package christmas.domain.benefit;

import christmas.domain.BenefitDetail;

public class NoDiscount implements Benefit {

    @Override
    public BenefitDetail detail() {
        return new BenefitDetail("", 0);
    }
}
