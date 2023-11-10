package christmas;

public class DDayDiscount {
    private final static int INITIAL_AMOUNT = 1000;
    private final static int ADDEND = 100;
    private final static int FIRST_DAY = 1;
    private final static int LAST_DAY = 25;
    private final int date;
    public DDayDiscount(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {
        if (date > LAST_DAY) {
            throw new IllegalArgumentException();
        }
    }

    public int discountByDate() {
        return INITIAL_AMOUNT + (date - FIRST_DAY) * ADDEND;
    }
}
