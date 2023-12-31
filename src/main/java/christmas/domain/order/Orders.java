package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import christmas.util.ErrorMessage;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Orders {
    private static final int MAXIMUM_SIZE = 20;
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateSize(orders);
        validateNotOnlyBeverage(orders);
        validateNotDuplicated(orders);
        this.orders = orders;
    }

    public Map<String, Integer> orderedMenu() {
        Map<String, Integer> orderedMenu = new HashMap<>();
        orders.forEach(order -> orderedMenu.put(order.menu().getName(), order.quantity()));
        return Collections.unmodifiableMap(orderedMenu);
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
            throw new IllegalArgumentException(ErrorMessage.MAXIMUM_ORDER_LIMIT.getMessage());
        }
    }

    private int sumQuantity(List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::quantity)
                .sum();
    }

    private void validateNotOnlyBeverage(List<Order> orders) {
        if (onlyBeverage(orders)) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_BEVERAGE.getMessage());
        }
    }

    private boolean onlyBeverage(List<Order> orders) {
        return orders.stream()
                .noneMatch(order -> order.menu().getMenuType() != MenuType.BEVERAGE);
    }

    private void validateNotDuplicated(List<Order> orders) {
        if (duplicated(orders)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
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
