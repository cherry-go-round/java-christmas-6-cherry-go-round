package christmas;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Menu {
    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6_000),
    TAPAS(MenuType.APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8_000),

    T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55_000),
    BARBECUE_RIP(MenuType.MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25_000),

    CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15_000),
    ICE_CREAM(MenuType.DESSERT, "아이스크림", 5_000),

    ZERO_COKE(MenuType.BEVERAGE, "제로콜라", 3_000),
    RED_WINE(MenuType.BEVERAGE, "레드와인", 60_000),
    CHAMPAGNE(MenuType.BEVERAGE, "샴페인", 25_000);

    private final MenuType menuType;
    private final String name;
    private final int price;

    Menu(MenuType menuType, String name, int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    public static Menu find(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
