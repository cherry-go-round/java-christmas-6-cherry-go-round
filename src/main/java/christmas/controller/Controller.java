package christmas.controller;

import christmas.EventDate;
import christmas.OrderParser;
import christmas.Orders;
import christmas.domain.Badge;
import christmas.domain.BadgeProvider;
import christmas.domain.AllBenefits;
import christmas.service.BenefitService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.Supplier;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final BenefitService benefitService;

    public Controller(InputView inputView, OutputView outputView, BenefitService benefitService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.benefitService = benefitService;
    }

    public void run() {
        outputView.printStartMessage();
        LocalDate reservationDate = finallyGet(this::readDate);

        String orderInput = inputView.readOrder();
        OrderParser orderParser = new OrderParser();
        Orders orders = orderParser.convert(orderInput);

        outputView.printResultTitle(reservationDate);
        Map<String, Integer> orderedMenu = orders.orderedMenu();
        outputView.printMenu(orderedMenu);

        int totalAmount = orders.totalAmount();
        outputView.printTotalAmountBeforeDiscount(totalAmount);

        Map<String, Integer> giveawayComposition = benefitService.getGiveawayComposition(orders);
        outputView.printGiveawayMenu(giveawayComposition);

        AllBenefits benefits = benefitService.getDetails(reservationDate, orders);

        Map<String, Integer> details = benefits.getDetails();
        outputView.printBenefitDetails(details);

        int totalBenefit = benefits.totalBenefit();
        outputView.printTotalBenefitAmount(totalBenefit);

        int totalDiscount = benefits.totalDiscount();
        int expectedAmount = totalAmount - totalDiscount;
        outputView.printExpectedAmountAfterDiscount(expectedAmount);

        BadgeProvider badgeProvider = new BadgeProvider();
        Badge badge = badgeProvider.provide(totalBenefit);
        outputView.printBadge(badge.getName());
    }

    private <T> T finallyGet(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    private LocalDate readDate() {
        int dayOfReservation = inputView.readDate();
        return EventDate.convert(dayOfReservation);
    }
}
