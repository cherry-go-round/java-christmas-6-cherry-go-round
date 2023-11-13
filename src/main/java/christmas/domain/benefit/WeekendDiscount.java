package christmas.domain.benefit;

import christmas.MenuType;
import christmas.Orders;
import christmas.domain.BenefitDetail;

public class WeekendDiscount implements Benefit {
    private static final String NAME = "주말 할인";
    private static final int DISCOUNT_UNIT = 2_023;
    private final Orders orders;

    public WeekendDiscount(Orders orders) {
        this.orders = orders;
    }

    @Override
    public BenefitDetail detail() {
        return new BenefitDetail(NAME, discount(orders));
    }

    private int discount(Orders orders) {
        return DISCOUNT_UNIT * orders.countByType(MenuType.MAIN);
    }
}
