package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.util.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderParser {
    private static final int MINIMUM_MENU_NUMBER = 1;
    public Orders convert(String input) {
        String[] orderPairs = input.split(",");
        List<Order> orderItems = Arrays.stream(orderPairs)
                .map(this::parseToOrder)
                .toList();
        return new Orders(orderItems);
    }

    private Order parseToOrder(String orderPair) {
        String[] split = orderPair.split("-");
        validateSize(split);

        String menuName = split[0];
        Menu menu = findMenuBy(menuName);

        String menuNumber = split[1];
        int parsedNumber = parseInt(menuNumber);
        validateNumber(parsedNumber);

        return new Order(menu, parsedNumber);
    }

    private void validateSize(String[] split) {
        if (split.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private Menu findMenuBy(String menuName) {
        try {
            return Menu.find(menuName);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private int parseInt(String menuNumber) {
        try {
            return Integer.parseInt(menuNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private void validateNumber(int parsedNumber) {
        if (parsedNumber < MINIMUM_MENU_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }
}
