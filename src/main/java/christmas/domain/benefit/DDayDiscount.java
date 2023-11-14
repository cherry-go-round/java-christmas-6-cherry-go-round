package christmas.domain.benefit;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DDayDiscount implements Benefit {
    private final static String NAME = "크리스마스 디데이 할인";
    private final static int INITIAL_AMOUNT = 1000;
    private final static int ADDEND = 100;
    private final static LocalDate FIRST_DATE = LocalDate.of(2023, 12, 1);

    private final LocalDate reservationDate;

    public DDayDiscount(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public BenefitDetail detail() {
        return new BenefitDetail(NAME, discount());
    }

    private int discount() {
        int between = (int) ChronoUnit.DAYS.between(FIRST_DATE, reservationDate);
        return INITIAL_AMOUNT + between * ADDEND;
    }
}
