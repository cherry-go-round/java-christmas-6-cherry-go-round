package christmas.discount;

import christmas.Orders;

public class NoDiscount implements Discount, WeekDiscount {
    @Override
    public int discount() {
        return 0;
    }

    @Override
    public int discount(Orders orders) {
        return 0;
    }
}
