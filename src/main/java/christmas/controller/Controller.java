package christmas.controller;

import christmas.domain.badge.Badge;
import christmas.domain.badge.BadgeProvider;
import christmas.domain.benefit.AllBenefits;
import christmas.domain.date.EventDate;
import christmas.domain.order.OrderParser;
import christmas.domain.order.Orders;
import christmas.service.Service;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.Supplier;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private Service service;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        setup();

        printMenu();
        printTotalAmountBeforeDiscount();
        printGiveaway();

        AllBenefits benefits = service.getDetails();

        printBenefitDetails(benefits);
        printTotalBenefitAmount(benefits);
        printAmountAfterDiscount(benefits);
        printBadge(benefits);
    }

    private void setup() {
        outputView.printStartMessage();
        LocalDate reservationDate = finallyGet(this::readDate);

        String orderInput = inputView.readOrder();
        OrderParser orderParser = new OrderParser();
        Orders orders = orderParser.convert(orderInput);

        service = new Service(reservationDate, orders);

        outputView.printResultTitle(reservationDate);
    }

    private void printMenu() {
        Map<String, Integer> orderedMenu = service.orderedMenu();
        outputView.printMenu(orderedMenu);
    }

    private void printTotalAmountBeforeDiscount() {
        int totalAmount = service.totalAmount();
        outputView.printTotalAmountBeforeDiscount(totalAmount);
    }

    private void printGiveaway() {
        Map<String, Integer> giveawayComposition = service.getGiveawayComposition();
        outputView.printGiveawayMenu(giveawayComposition);
    }

    private void printBenefitDetails(AllBenefits benefits) {
        Map<String, Integer> details = benefits.getDetails();
        outputView.printBenefitDetails(details);
    }

    private void printTotalBenefitAmount(AllBenefits benefits) {
        int totalBenefit = benefits.totalBenefit();
        outputView.printTotalBenefitAmount(totalBenefit);
    }

    private void printAmountAfterDiscount(AllBenefits benefits) {
        int totalAmount = service.totalAmount();
        int totalDiscount = benefits.totalDiscount();
        int expectedAmount = totalAmount - totalDiscount;
        outputView.printExpectedAmountAfterDiscount(expectedAmount);
    }

    private LocalDate readDate() {
        int dayOfReservation = inputView.readDate();
        return EventDate.convert(dayOfReservation);
    }

    private void printBadge(AllBenefits benefits) {
        BadgeProvider badgeProvider = new BadgeProvider();
        Badge badge = badgeProvider.provide(benefits.totalBenefit());
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
}
