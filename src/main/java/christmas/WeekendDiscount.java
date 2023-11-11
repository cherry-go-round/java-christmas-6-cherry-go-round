package christmas;

public class WeekendDiscount {
    private static final int DISCOUNT_UNIT = 2_023;
    private static final int MINIMUM_TOTAL_AMOUNT = 10_000;

    public int discount(Orders orders) {
        if (orders.totalAmountIsUnder(MINIMUM_TOTAL_AMOUNT)) {
            return 0;
        }
        return DISCOUNT_UNIT * orders.countByType(MenuType.MAIN);
    }
}
