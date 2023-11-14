package christmas;

import java.util.Arrays;
import java.util.List;

public class Orders {
    private static final int MAXIMUM_SIZE = 20;
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateSize(orders);
        validateNotOnlyBeverage(orders);
        validateNotDuplicated(orders);
        this.orders = orders;
    }

    public int countByType(MenuType menuType) {
        return orders.stream()
                .filter(order -> order.menu().getMenuType() == menuType)
                .mapToInt(Order::quantity)
                .sum();
    }

    public int totalAmount() {
        return orders.stream()
                .mapToInt(order -> order.menu().getPrice() * order.quantity())
                .sum();
    }

    private void validateSize(List<Order> orders) {
        if (sumQuantity(orders) > MAXIMUM_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private int sumQuantity(List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::quantity)
                .sum();
    }

    private void validateNotOnlyBeverage(List<Order> orders) {
        if (onlyBeverage(orders)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean onlyBeverage(List<Order> orders) {
        return orders.stream()
                .noneMatch(order -> order.menu().getMenuType() != MenuType.BEVERAGE);
    }

    private void validateNotDuplicated(List<Order> orders) {
        if (duplicated(orders)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean duplicated(List<Order> orders) {
        List<Menu> menus = orders.stream()
                .map(Order::menu)
                .toList();
        return menus.size() != menus.stream()
                .distinct()
                .count();
    }
}
