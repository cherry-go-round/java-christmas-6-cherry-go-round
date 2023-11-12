package christmas.discount;

import christmas.Orders;
import christmas.StarDay;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Benefit {
    private static final int MINIMUM_TOTAL_AMOUNT = 10_000;
    private static final NoDiscount NONE = new NoDiscount();

    private final LocalDate reservationDate;
    private Discount dDayDiscount;
    private WeekDiscount weekDiscount;
    private Discount specialDiscount;

    public Benefit(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
        this.dDayDiscount = NONE;
        this.weekDiscount = NONE;
        this.specialDiscount = NONE;
    }

    public int totalDiscount(Orders orders) {
        checkTotalAmountAndSelectDiscount(orders);
        return dDayDiscount.discount() + weekDiscount.discount(orders) + specialDiscount.discount();
    }

    private void checkTotalAmountAndSelectDiscount(Orders orders) {
        if (orders.totalAmount() >= MINIMUM_TOTAL_AMOUNT) {
            dDayDiscount = selectdDayDiscount(reservationDate);
            weekDiscount = selectWeekDiscount(reservationDate);
            specialDiscount = selectSpecialDiscount(reservationDate);
        }
    }

    private Discount selectdDayDiscount(LocalDate reservationDate) {
        LocalDate lastDate = LocalDate.of(2023, 12, 25);
        if (reservationDate.isAfter(lastDate)) {
            return NONE;
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

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY
                || dayOfWeek == DayOfWeek.SATURDAY;
    }

    private Discount selectSpecialDiscount(LocalDate reservationDate) {
        if (StarDay.contains(reservationDate)) {
            return new SpecialDiscount();
        }
        return NONE;
    }
}
