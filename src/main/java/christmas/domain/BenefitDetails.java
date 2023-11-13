package christmas.domain;

public class BenefitDetails {
    BenefitDetail dDayDiscountDetail;
    BenefitDetail weekDayDiscountDetail;
    BenefitDetail specialDiscountDetail;
    BenefitDetail giveawayDetail;

    public BenefitDetails(BenefitDetail dDayDiscountDetail, BenefitDetail weekDayDiscountDetail,
                          BenefitDetail specialDiscountDetail, BenefitDetail giveawayDetail) {
        this.dDayDiscountDetail = dDayDiscountDetail;
        this.weekDayDiscountDetail = weekDayDiscountDetail;
        this.specialDiscountDetail = specialDiscountDetail;
        this.giveawayDetail = giveawayDetail;
    }

    public int totalDiscount() {
        return dDayDiscountDetail.amount() + weekDayDiscountDetail.amount() + specialDiscountDetail.amount();
    }

    public int totalBenefit() {
        return totalDiscount() + giveawayDetail.amount();
    }
}
