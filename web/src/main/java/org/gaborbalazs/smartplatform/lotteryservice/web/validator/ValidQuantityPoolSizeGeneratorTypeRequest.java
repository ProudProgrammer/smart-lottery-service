package org.gaborbalazs.smartplatform.lotteryservice.web.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuantityPoolSizeGeneratorTypeRequestValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidQuantityPoolSizeGeneratorTypeRequest {

    String message() default "Invalid quantity, poolSize, generatorType";

    Class[] groups() default {};

    Class[] payload() default {};
}
