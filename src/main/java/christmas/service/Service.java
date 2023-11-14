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

public class Service {
    private static final int MINIMUM_TOTAL_AMOUNT = 10_000;
    private static final int GIVEAWAY_AMOUNT = 120_000;
    private static final NoBenefit NONE = new NoBenefit();

    private final LocalDate reservationDate;
    private final Orders orders;

    private final Benefit dDayDiscount;
    private final Benefit weekDiscount;
    private final Benefit specialDiscount;
    private final Benefit giveaway;

    public Service(LocalDate reservationDate, Orders orders) {
        this.reservationDate = reservationDate;
        this.orders = orders;

        dDayDiscount = decideDDayDiscount();
        weekDiscount = decideWeekDiscount();
        specialDiscount = decideSpecialDiscount();
        giveaway = decideGiveaway();
    }

    public Map<String, Integer> orderedMenu() {
        return orders.orderedMenu();
    }

    public int totalAmount() {
        return orders.totalAmount();
    }

    public Map<String, Integer> getGiveawayComposition() {
        if (giveaway instanceof NoBenefit) {
            return Collections.emptyMap();
        }
        Giveaway converted = (Giveaway) giveaway;
        return converted.getGiveawayComposition();
    }

    public AllBenefits getDetails() {
        return new AllBenefitsBuilder()
                .dDayDiscount(dDayDiscount)
                .weekDiscount(weekDiscount)
                .specialDiscount(specialDiscount)
                .giveaway(giveaway)
                .build();
    }

    private Benefit decideDDayDiscount() {
        if (notSatisfiedMinimum()) {
            return NONE;
        }
        LocalDate lastDate = LocalDate.of(2023, 12, 25);
        if (reservationDate.isAfter(lastDate)) {
            return NONE;
        }
        return new DDayDiscount(reservationDate);
    }

    private Benefit decideWeekDiscount() {
        if (notSatisfiedMinimum()) {
            return NONE;
        }
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        if (isWeekend(dayOfWeek)) {
            return new WeekendDiscount(orders);
        }
        return new WeekDayDiscount(orders);
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    private Benefit decideSpecialDiscount() {
        if (notSatisfiedMinimum()) {
            return NONE;
        }
        if (StarDay.contains(reservationDate)) {
            return new SpecialDiscount();
        }
        return NONE;
    }

    private boolean notSatisfiedMinimum() {
        return orders.totalAmount() < MINIMUM_TOTAL_AMOUNT;
    }

    private Benefit decideGiveaway() {
        if (orders.totalAmount() >= GIVEAWAY_AMOUNT) {
            return new Giveaway();
        }
        return NONE;
    }
}
