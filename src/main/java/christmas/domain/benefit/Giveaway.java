package christmas.domain.benefit;

import christmas.Menu;
import christmas.domain.BenefitDetail;

public class Giveaway implements Benefit{
    private static final String NAME = "증정 이벤트";
    private static final Menu GIVEAWAY_MENU = Menu.CHAMPAGNE;

    @Override
    public BenefitDetail detail() {
        return new BenefitDetail(NAME, GIVEAWAY_MENU.getPrice());
    }
}
