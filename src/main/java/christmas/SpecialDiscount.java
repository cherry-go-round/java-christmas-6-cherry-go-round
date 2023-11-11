package christmas;

import java.time.LocalDate;

public class SpecialDiscount {
    private static final int DISCOUNT_AMOUNT = 1_000;
    private static final int NONE = 0;
    public int discount(LocalDate reservationDate) {
        if (StarDay.contains(reservationDate)) {
            return DISCOUNT_AMOUNT;
        }
        return NONE;
    }
}
