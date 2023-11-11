package christmas;

import java.time.LocalDate;
import java.util.Arrays;

public enum StarDay {
    FIRST_SUNDAY(LocalDate.of(2023, 12, 3)),
    SECOND_SUNDAY(LocalDate.of(2023, 12, 10)),
    THIRD_SUNDAY(LocalDate.of(2023, 12, 17)),
    FOURTH_SUNDAY(LocalDate.of(2023, 12, 24)),
    FIFTH_SUNDAY(LocalDate.of(2023, 12, 31)),

    CHRISTMAS(LocalDate.of(2023, 12, 25));

    private final LocalDate localDate;

    StarDay(LocalDate localDate) {
        this.localDate = localDate;
    }

    public static boolean contains(LocalDate reservationDate) {
        return Arrays.stream(StarDay.values())
                .anyMatch(starDay -> starDay.localDate.equals(reservationDate));
    }
}
