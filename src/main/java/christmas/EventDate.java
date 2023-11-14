package christmas;

import java.time.DateTimeException;
import java.time.LocalDate;

public class EventDate {
    public static LocalDate convert(int day) {
        try {
            return LocalDate.of(2023, 12, day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException();
        }
    }
}
