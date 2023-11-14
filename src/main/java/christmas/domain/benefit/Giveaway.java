package christmas.domain.benefit;

import christmas.domain.menu.Menu;
import java.util.HashMap;
import java.util.Map;

public class Giveaway implements Benefit {
    private static final String NAME = "증정 이벤트";
    private static final Menu GIVEAWAY_MENU = Menu.CHAMPAGNE;
    private static final int NUMBER_OF_GIVEAWAY = 1;

    @Override
    public BenefitDetail detail() {
        return new BenefitDetail(NAME, GIVEAWAY_MENU.getPrice());
    }

    public Map<String, Integer> getGiveawayComposition() {
        Map<String, Integer> giveawayComposition = new HashMap<>();
        giveawayComposition.put(GIVEAWAY_MENU.getName(), NUMBER_OF_GIVEAWAY);
        return giveawayComposition;
    }
}
