package christmas;

import christmas.controller.Controller;
import christmas.service.BenefitService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BenefitService benefitService = new BenefitService();
        Controller controller = new Controller(inputView, outputView, benefitService);

        controller.run();
    }
}
