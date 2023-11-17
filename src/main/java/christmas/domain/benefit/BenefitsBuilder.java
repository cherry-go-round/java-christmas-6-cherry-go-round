package christmas.domain.benefit;

public class BenefitsBuilder {
    private Benefit dDayDiscount;
    private Benefit weekDiscount;
    private Benefit specialDiscount;
    private Benefit giveaway;

    public BenefitsBuilder dDayDiscount(Benefit dDayDiscount) {
        this.dDayDiscount = dDayDiscount;
        return this;
    }

    public BenefitsBuilder weekDiscount(Benefit weekDiscount) {
        this.weekDiscount = weekDiscount;
        return this;
    }

    public BenefitsBuilder specialDiscount(Benefit specialDiscount) {
        this.specialDiscount = specialDiscount;
        return this;
    }

    public BenefitsBuilder giveaway(Benefit giveaway) {
        this.giveaway = giveaway;
        return this;
    }

    public Benefits build() {
        return new Benefits(dDayDiscount, weekDiscount, specialDiscount, giveaway);
    }
}