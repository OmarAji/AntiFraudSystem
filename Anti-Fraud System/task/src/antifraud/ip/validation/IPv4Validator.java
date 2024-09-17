package antifraud.ip.validation;

import antifraud.ip.exception.IpWrongFormattedException;
import antifraud.utility.IpUtility;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IPv4Validator implements ConstraintValidator<ValidIPv4, String> {
    @Override
    public boolean isValid(String ip, ConstraintValidatorContext constraintValidatorContext) {
        if(!IpUtility.isValid(ip)) {
            throw new IpWrongFormattedException("IP address is invalid");
        }
        return IpUtility.isValid(ip);
    }
}
