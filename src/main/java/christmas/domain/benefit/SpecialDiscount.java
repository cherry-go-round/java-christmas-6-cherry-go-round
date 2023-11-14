package christmas.domain.benefit;

public class SpecialDiscount implements Benefit {
    private static final String NAME = "특별 할인";
    private static final int DISCOUNT_AMOUNT = 1_000;

    @Override
    public BenefitDetail detail() {
        return new BenefitDetail(NAME, discount());
    }

    private int discount() {
        return DISCOUNT_AMOUNT;
    }
}
