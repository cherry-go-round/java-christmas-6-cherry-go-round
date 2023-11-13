package christmas.domain;

public class BenefitDetailsBuilder {
    private BenefitDetail dDayDiscountDetail;
    private BenefitDetail weekDayDiscountDetail;
    private BenefitDetail specialDiscountDetail;
    private BenefitDetail giveawayDetail;

    public BenefitDetailsBuilder dDay(BenefitDetail dDayDiscountDetail) {
        this.dDayDiscountDetail = dDayDiscountDetail;
        return this;
    }

    public BenefitDetailsBuilder weekDay(BenefitDetail weekDayDiscountDetail) {
        this.weekDayDiscountDetail = weekDayDiscountDetail;
        return this;
    }

    public BenefitDetailsBuilder special(BenefitDetail specialDiscountDetail) {
        this.specialDiscountDetail = specialDiscountDetail;
        return this;
    }

    public BenefitDetailsBuilder giveaway(BenefitDetail giveawayDetail) {
        this.giveawayDetail = giveawayDetail;
        return this;
    }

    public BenefitDetails build() {
        return new BenefitDetails(dDayDiscountDetail, weekDayDiscountDetail, specialDiscountDetail, giveawayDetail);
    }
}