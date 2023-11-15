package christmas.domain.benefit;

import java.util.HashMap;
import java.util.Map;

public class Benefits {
    private final Benefit dDayDiscount;
    private final Benefit weekDiscount;
    private final Benefit specialDiscount;
    private final Benefit giveaway;

    public Benefits(Benefit dDayDiscount, Benefit weekDiscount,
                    Benefit specialDiscount, Benefit giveaway) {
        this.dDayDiscount = dDayDiscount;
        this.weekDiscount = weekDiscount;
        this.specialDiscount = specialDiscount;
        this.giveaway = giveaway;
    }

    public Map<String, Integer> getDetails() {
        Map<String, Integer> details = new HashMap<>();

        putBenefitDetails(details, dDayDiscount);
        putBenefitDetails(details, weekDiscount);
        putBenefitDetails(details, specialDiscount);
        putBenefitDetails(details, giveaway);

        return details;
    }

    public int totalDiscount() {
        BenefitDetail dDayDiscountDetail = dDayDiscount.detail();
        BenefitDetail weekDiscountDetail = weekDiscount.detail();
        BenefitDetail specialDiscountDetail = specialDiscount.detail();
        return dDayDiscountDetail.amount() + weekDiscountDetail.amount() + specialDiscountDetail.amount();
    }

    public int totalBenefit() {
        BenefitDetail giveawayDetail = giveaway.detail();
        return totalDiscount() + giveawayDetail.amount();
    }

    private void putBenefitDetails(Map<String, Integer> details, Benefit benefit) {
        if (benefit instanceof NoBenefit) {
            return;
        }
        BenefitDetail benefitDetail = benefit.detail();
        details.put(benefitDetail.name(), benefitDetail.amount());
    }
}
