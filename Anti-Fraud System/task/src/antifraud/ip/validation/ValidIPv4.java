package antifraud.ip.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IPv4Validator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIPv4 {
    String message() default "Invalid IPv4 address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
