package christmas.service;

import christmas.domain.benefit.Benefits;
import java.util.Map;

public class BenefitsService {
    private final Benefits benefits;

    public BenefitsService(Benefits benefits) {
        this.benefits = benefits;
    }

    public Map<String, Integer> getDetails() {
        return benefits.getDetails();
    }

    public int totalBenefit() {
        return benefits.totalBenefit();
    }

    public int totalDiscount() {
        return benefits.totalDiscount();
    }
}
