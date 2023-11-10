package christmas;

import java.util.Arrays;
import java.util.List;

public class Orders {
    private final List<Order> orders;

    public Orders(Order... orders) {
        validateNotDuplicated(orders);
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

    private int totalAmount() {
        return orders.stream()
                .mapToInt(order -> order.menu().getPrice() * order.quantity())
                .sum();
    }
}
