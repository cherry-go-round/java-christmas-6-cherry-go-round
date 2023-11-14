package christmas.util;

public class Util {
    public static void notEmpty(String input, ErrorMessage expectedError) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(expectedError.getMessage());
        }
    }
}
