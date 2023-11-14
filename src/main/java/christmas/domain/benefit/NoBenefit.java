package christmas.domain.benefit;

public class NoBenefit implements Benefit {

    @Override
    public BenefitDetail detail() {
        return new BenefitDetail("", 0);
    }
}
