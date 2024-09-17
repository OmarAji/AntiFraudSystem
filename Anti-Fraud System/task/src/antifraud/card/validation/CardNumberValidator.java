package antifraud.card.validation;

import antifraud.card.exception.InvalidCardNumberException;
import antifraud.utility.CardUtility;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CardNumberValidator implements ConstraintValidator<ValidCardNumber, String> {
    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (!CardUtility.isValid(cardNumber)) {
            throw new InvalidCardNumberException("wrong card number");
        }
        return CardUtility.isValid(cardNumber);
    }
}
