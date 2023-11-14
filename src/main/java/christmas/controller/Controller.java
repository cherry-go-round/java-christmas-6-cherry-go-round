package christmas.controller;

import christmas.domain.badge.Badge;
import christmas.domain.badge.BadgeProvider;
import christmas.domain.benefit.Benefits;
import christmas.domain.date.EventDate;
import christmas.domain.order.OrderParser;
import christmas.domain.order.Orders;
import christmas.service.BenefitsService;
import christmas.service.ReservationService;
import christmas.util.ErrorOutput;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.Supplier;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private ReservationService reservationService;
    private BenefitsService benefitsService;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        setup();

        printMenu();
        printTotalAmountBeforeDiscount();
        printGiveaway();

        Benefits benefits = reservationService.allBenefits();
        benefitsService = new BenefitsService(benefits);

        printBenefitDetails();
        printTotalBenefitAmount();
        printAmountAfterDiscount();
        printBadge();
    }

    private void setup() {
        outputView.printStartMessage();
        LocalDate reservationDate = finallyGet(this::readDate);
        Orders orders = finallyGet(this::readOrders);
        reservationService = new ReservationService(reservationDate, orders);

        outputView.printResultTitle(reservationDate);
    }

    private void printMenu() {
        Map<String, Integer> orderedMenu = reservationService.orderedMenu();
        outputView.printMenu(orderedMenu);
    }

    private void printTotalAmountBeforeDiscount() {
        int totalAmount = reservationService.totalAmount();
        outputView.printTotalAmountBeforeDiscount(totalAmount);
    }

    private void printGiveaway() {
        Map<String, Integer> giveawayComposition = reservationService.getGiveawayComposition();
        outputView.printGiveawayMenu(giveawayComposition);
    }

    private void printBenefitDetails() {
        Map<String, Integer> details = benefitsService.getDetails();
        outputView.printBenefitDetails(details);
    }

    private void printTotalBenefitAmount() {
        int totalBenefit = benefitsService.totalBenefit();
        outputView.printTotalBenefitAmount(totalBenefit);
    }

    private void printAmountAfterDiscount() {
        int totalAmount = reservationService.totalAmount();
        int totalDiscount = benefitsService.totalDiscount();
        int expectedAmount = totalAmount - totalDiscount;
        outputView.printExpectedAmountAfterDiscount(expectedAmount);
    }

    private void printBadge() {
        BadgeProvider badgeProvider = new BadgeProvider();
        Badge badge = badgeProvider.provide(benefitsService.totalBenefit());
        outputView.printBadge(badge.getName());
    }

    private LocalDate readDate() {
        int dayOfReservation = inputView.readDate();
        return EventDate.convert(dayOfReservation);
    }

    private Orders readOrders() {
        String orderInput = inputView.readOrder();
        OrderParser orderParser = new OrderParser();
        return orderParser.convert(orderInput);
    }

    private <T> T finallyGet(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                String message = exception.getMessage();
                ErrorOutput.printError(message);
            }
        }
    }
}
