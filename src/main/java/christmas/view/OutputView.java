package christmas.view;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;

public class OutputView {
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String RESULT_TITLE = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String MENU_TITLE = "<주문 메뉴>";
    private static final String UNIT_OF_NUMBER = "개";
    private static final String UNIT_OF_MONEY = "원";
    private static final DecimalFormat MONEY_FORMATTER = new DecimalFormat("###.###");

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printResultTitle(LocalDate localDate) {
        System.out.printf(RESULT_TITLE, localDate.getMonthValue(), localDate.getDayOfMonth());
        System.out.println();
    }

    public void printMenu(Map<String, Integer> menu) {
        System.out.println(MENU_TITLE);
        menu.keySet()
                .forEach(name -> printMenuElements(name, menu.get(name)));
    }

    public void printTotalAmountBeforeDiscount(int amount) {
        System.out.println(MONEY_FORMATTER.format(amount) + UNIT_OF_MONEY);
    }

    private void printMenuElements(String name, int number) {
        System.out.println(name + " " + number + UNIT_OF_NUMBER);
    }
}