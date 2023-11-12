package christmas.discount;

public class SpecialDiscount implements Discount{
    private static final int DISCOUNT_AMOUNT = 1_000;

    @Override
    public int discount() {
        return DISCOUNT_AMOUNT;
    }
}
