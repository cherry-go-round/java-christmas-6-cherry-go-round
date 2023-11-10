package christmas;

public class WeekDayDiscount {
    private static final int DISCOUNT_UNIT = 2_023;
    private static final int MINIMUM_TOTAL_AMOUNT = 10_000;
    private final Orders orders;

    public WeekDayDiscount(Orders orders) {
        this.orders = orders;
    }

    public int discount() {
        if (orders.totalAmountIsUnder(MINIMUM_TOTAL_AMOUNT)) {
            return 0;
        }
        return DISCOUNT_UNIT * orders.countByType(MenuType.DESSERT);
    }
}
