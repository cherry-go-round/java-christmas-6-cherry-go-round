package christmas;

import java.util.Arrays;
import java.util.List;

public class Orders {
    private static final int MAXIMUM_SIZE = 20;
    private final List<Order> orders;

    public Orders(Order... orders) {
        validateNotDuplicated(orders);
        validateSize(orders);
        this.orders = Arrays.asList(orders);
    }

    public int countByType(MenuType menuType) {
        return orders.stream()
                .filter(order -> order.menu().getMenuType() == menuType)
                .mapToInt(Order::quantity)
                .sum();
    }

    public boolean totalAmountIsUnder(int number) {
        return totalAmount() < number;
    }

    private void validateNotDuplicated(Order[] orders) {
        if (duplicated(orders)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean duplicated(Order[] orders) {
        List<Menu> menus = Arrays.stream(orders)
                .map(Order::menu)
                .toList();
        return menus.size() != menus.stream()
                .distinct()
                .count();
    }

    private void validateSize(Order[] orders) {
        if (sumQuantity(orders) > MAXIMUM_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private int sumQuantity(Order[] orders) {
        return Arrays.stream(orders)
                .mapToInt(Order::quantity)
                .sum();
    }

    private int totalAmount() {
        return orders.stream()
                .mapToInt(order -> order.menu().getPrice() * order.quantity())
                .sum();
    }
}
