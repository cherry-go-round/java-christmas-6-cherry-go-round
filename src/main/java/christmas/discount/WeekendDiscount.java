package christmas.discount;

import christmas.MenuType;
import christmas.Orders;

public class WeekendDiscount implements WeekDiscount {
    private static final int DISCOUNT_UNIT = 2_023;

    @Override
    public int discount(Orders orders) {
        return DISCOUNT_UNIT * orders.countByType(MenuType.MAIN);
    }
}
