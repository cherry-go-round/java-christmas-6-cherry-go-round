package christmas.service;

import christmas.domain.benefit.AllBenefits;
import christmas.domain.benefit.AllBenefitsBuilder;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.DDayDiscount;
import christmas.domain.benefit.Giveaway;
import christmas.domain.benefit.NoBenefit;
import christmas.domain.benefit.SpecialDiscount;
import christmas.domain.benefit.WeekDayDiscount;
import christmas.domain.benefit.WeekendDiscount;
import christmas.domain.date.StarDay;
import christmas.domain.order.Orders;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

public class BenefitService {
    private static final int MINIMUM_TOTAL_AMOUNT = 10_000;
    private static final int GIVEAWAY_AMOUNT = 120_000;
    private static final NoBenefit NONE = new NoBenefit();

    private Benefit dDayDiscount = NONE;
    private Benefit weekDiscount = NONE;
    private Benefit specialDiscount = NONE;
    private Benefit giveaway = NONE;

    public Map<String, Integer> getGiveawayComposition(Orders orders) {
        judgeGiveaway(orders);
        if (giveaway instanceof NoBenefit) {
            return Collections.emptyMap();
        }
        Giveaway converted = (Giveaway) giveaway;
        return converted.getGiveawayComposition();
    }

    public AllBenefits getDetails(LocalDate reservationDate, Orders orders) {
        checkTotalAmountAndDecideDiscount(reservationDate, orders);

        judgeGiveaway(orders);

        return new AllBenefitsBuilder()
                .dDayDiscount(dDayDiscount)
                .weekDiscount(weekDiscount)
                .specialDiscount(specialDiscount)
                .giveaway(giveaway)
                .build();
    }

    private void checkTotalAmountAndDecideDiscount(LocalDate reservationDate, Orders orders) {
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
