package christmas;

public class DDayDiscount {
    private final int date;

    public DDayDiscount(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {
        if (date > 25) {
            throw new IllegalArgumentException();
        }
    }

    public int discountByDate() {
        return 1000 + (date - 1) * 100;
    }
}
