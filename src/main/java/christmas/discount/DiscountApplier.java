package christmas.discount;

import christmas.Orders;
import christmas.StarDay;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DiscountApplier {
    private static final int MINIMUM_TOTAL_AMOUNT = 10_000;

    private final LocalDate reservationDate;
    private Discount dDayDiscount;
    private WeekDiscount weekDiscount;
    private Discount specialDiscount;

    public DiscountApplier(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
        this.dDayDiscount = new NoDiscount();
        this.weekDiscount = new NoDiscount();
        this.specialDiscount = new NoDiscount();
    }

    public int totalDiscount(Orders orders) {
        if (!orders.totalAmountIsUnder(MINIMUM_TOTAL_AMOUNT)) {
            dDayDiscount = selectdDayDiscount(reservationDate);
            weekDiscount = selectWeekDiscount(reservationDate);
            specialDiscount = selectSpecialDiscount(reservationDate);
        }
        return dDayDiscount.discount() + weekDiscount.discount(orders) + specialDiscount.discount();
    }

    private Discount selectdDayDiscount(LocalDate reservationDate) {
        LocalDate lastDate = LocalDate.of(2023, 12, 25);
        if (reservationDate.isAfter(lastDate)) {
            return new NoDiscount();
        }
        return new DDayDiscount(reservationDate);
    }

    private WeekDiscount selectWeekDiscount(LocalDate reservationDate) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        if (isWeekend(dayOfWeek)) {
            return new WeekendDiscount();
        }
        return new WeekDayDiscount();
    }

    private Discount selectSpecialDiscount(LocalDate reservationDate) {
        if (StarDay.contains(reservationDate)) {
            return new SpecialDiscount();
        }
        return new NoDiscount();
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY
                || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
