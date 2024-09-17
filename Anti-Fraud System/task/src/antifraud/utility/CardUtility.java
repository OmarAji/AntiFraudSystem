package antifraud.utility;

import java.util.stream.IntStream;

public class CardUtility {

    public static boolean isValid(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 16 || !cardNumber.matches("\\d+")) {
            return false;
        }
        int totalSum = IntStream.range(0, cardNumber.length())
                .map(i -> {
                    int digit = Character.getNumericValue(cardNumber.charAt(15 - i));
                    if (i % 2 == 1) {
                        digit *= 2;
                        if (digit > 9) digit -= 9;
                    }
                    return digit;
                })
                .sum();
        if (totalSum % 10 != 0) {

            return false;
        }
        return true;
    }
}
