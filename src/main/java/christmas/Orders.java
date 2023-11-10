package christmas;

import java.util.Arrays;
import java.util.List;

public class Orders {
    private final List<Order> orders;

    public Orders(Order... orders) {
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

    private int totalAmount() {
        return orders.stream()
                .mapToInt(order -> order.menu().getPrice() * order.quantity())
                .sum();
    }
}
