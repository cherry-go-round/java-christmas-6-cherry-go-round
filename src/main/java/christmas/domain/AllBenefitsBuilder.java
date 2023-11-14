package christmas.domain;

import christmas.domain.benefit.Benefit;

public class AllBenefitsBuilder {
    private Benefit dDayDiscount;
    private Benefit weekDiscount;
    private Benefit specialDiscount;
    private Benefit giveaway;

    public AllBenefitsBuilder dDayDiscount(Benefit dDayDiscount) {
        this.dDayDiscount = dDayDiscount;
        return this;
    }

    public AllBenefitsBuilder weekDiscount(Benefit weekDiscount) {
        this.weekDiscount = weekDiscount;
        return this;
    }

    public AllBenefitsBuilder specialDiscount(Benefit specialDiscount) {
        this.specialDiscount = specialDiscount;
        return this;
    }

    public AllBenefitsBuilder giveaway(Benefit giveaway) {
        this.giveaway = giveaway;
        return this;
    }

    public AllBenefits build() {
        return new AllBenefits(dDayDiscount, weekDiscount, specialDiscount, giveaway);
    }
}