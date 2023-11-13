package christmas.view;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;

public class OutputView {
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String RESULT_TITLE = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String MENU_TITLE = "<주문 메뉴>";
    private static final String TOTAL_AMOUNT_BEFORE_DISCOUNT_TITLE = "<할인 전 총주문 금액>";
    private static final String TOTAL_BENEFIT_AMOUNT_TITLE = "<총혜택 금액>";
    private static final String GIVEAWAY_MENU_TITLE = "<증정 메뉴>";
    private static final String EXPECTED_AMOUNT_TITLE = "<할인 후 예상 결제 금액>";
    private static final String BADGE_TITLE = "<12월 이벤트 배지>";
    private static final String UNIT_OF_NUMBER = "개";
    private static final String UNIT_OF_MONEY = "원";
    private static final String NONE = "없음";
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
        System.out.println(TOTAL_AMOUNT_BEFORE_DISCOUNT_TITLE);
        System.out.println(MONEY_FORMATTER.format(amount) + UNIT_OF_MONEY);
    }

    public void printGiveawayMenu(Map<String, Integer> giveawayComposition) {
        System.out.println(GIVEAWAY_MENU_TITLE);
        giveawayComposition.keySet()
                .forEach(name -> printMenuElements(name, giveawayComposition.get(name)));
    }

    public void printNone() {
        System.out.println(NONE);
    }

    public void printTotalBenefitAmount(int amount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT_TITLE);
        System.out.println("-" + MONEY_FORMATTER.format(amount) + UNIT_OF_MONEY);
    }

    public void printExpectedAmount(int amount) {
        System.out.println(EXPECTED_AMOUNT_TITLE);
        System.out.println(MONEY_FORMATTER.format(amount) + UNIT_OF_MONEY);
    }

    public void printBadge(String name) {
        System.out.println(BADGE_TITLE);
        System.out.println(name);
    }

    private void printMenuElements(String name, int number) {
        System.out.println(name + " " + number + UNIT_OF_NUMBER);
    }
}
