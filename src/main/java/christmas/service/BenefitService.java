package christmas.service;

import christmas.Orders;
import christmas.StarDay;
import christmas.domain.BenefitDetail;
import christmas.domain.BenefitDetails;
import christmas.domain.BenefitDetailsBuilder;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.DDayDiscount;
import christmas.domain.benefit.Giveaway;
import christmas.domain.benefit.NoBenefit;
import christmas.domain.benefit.SpecialDiscount;
import christmas.domain.benefit.WeekDayDiscount;
import christmas.domain.benefit.WeekendDiscount;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;

public class BenefitService {
    private static final int MINIMUM_TOTAL_AMOUNT = 10_000;
    private static final int GIVEAWAY_AMOUNT = 120_000;
    private static final NoBenefit NONE = new NoBenefit();

    private final LocalDate reservationDate;
    private Benefit dDayDiscount = NONE;
    private Benefit weekDiscount = NONE;
    private Benefit specialDiscount = NONE;
    private Benefit giveaway = NONE;

    public BenefitService(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Map<String, Integer> getGiveawayComposition() {
        if (giveaway instanceof NoBenefit) {
            throw new NoSuchElementException();
        }
        Giveaway converted = (Giveaway) giveaway;
        return converted.getGiveawayComposition();
    }

    public BenefitDetails getDetails(Orders orders) {
        checkTotalAmountAndDecideDiscount(orders);
        BenefitDetail dDayDiscountDetail = dDayDiscount.detail();
        BenefitDetail weekDayDiscountDetail = weekDiscount.detail();
        BenefitDetail specialDiscountDetail = specialDiscount.detail();

        judgeGiveaway(orders);
        BenefitDetail giveawayDetail = giveaway.detail();

        return new BenefitDetailsBuilder()
                .dDay(dDayDiscountDetail)
                .weekDay(weekDayDiscountDetail)
                .special(specialDiscountDetail)
                .giveaway(giveawayDetail)
                .build();
    }

    private void checkTotalAmountAndDecideDiscount(Orders orders) {
        if (orders.totalAmount() >= MINIMUM_TOTAL_AMOUNT) {
            decideDDayDiscount(reservationDate);
            decideWeekDiscount(reservationDate, orders);
            decideSpecialDiscount(reservationDate);
        }
    }

    private void decideDDayDiscount(LocalDate reservationDate) {
        LocalDate lastDate = LocalDate.of(2023, 12, 25);
        if (reservationDate.isAfter(lastDate)) {
            return;
        }
        dDayDiscount = new DDayDiscount(reservationDate);
    }

    private void decideWeekDiscount(LocalDate reservationDate, Orders orders) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        if (isWeekend(dayOfWeek)) {
            weekDiscount = new WeekendDiscount(orders);
            return;
        }
        weekDiscount = new WeekDayDiscount(orders);
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    private void decideSpecialDiscount(LocalDate reservationDate) {
        if (StarDay.contains(reservationDate)) {
            specialDiscount = new SpecialDiscount();
        }
    }

    private void judgeGiveaway(Orders orders) {
        if (orders.totalAmount() >= GIVEAWAY_AMOUNT) {
            giveaway = new Giveaway();
        }
    }
}
