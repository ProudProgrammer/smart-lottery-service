package org.gaborbalazs.smartplatform.lotteryservice.web.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuantityAndPoolSizeValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidQuantityAndPoolSize {

    String message() default "Invalid quantity and poolSize";

    Class[] groups() default {};

    Class[] payload() default {};
}
