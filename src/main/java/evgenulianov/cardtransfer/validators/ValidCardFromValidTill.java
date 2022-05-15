package evgenulianov.cardtransfer.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidatorCardFromValidTill.class)
@Documented
public @interface ValidCardFromValidTill {

    String message() default "{cardFromValidTill must be 4 digits \"mmYY\"}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
