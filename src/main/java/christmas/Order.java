package christmas;

public record Order(Menu menu, int quantity) {
    private static final int MIN_QUANTITY = 1;

    public Order {
        validate(quantity);
    }

    private void validate(int quantity) {
        if (quantity < MIN_QUANTITY) {
            throw new IllegalArgumentException();
        }
    }
}
