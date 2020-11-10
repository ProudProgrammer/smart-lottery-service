package org.gaborbalazs.smartplatform.lotteryservice.web.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LotteryTypeGeneratorTypeRequestValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLotteryTypeGeneratorTypeRequest {

    String message() default "Invalid lotteryType, generatorType";

    Class[] groups() default {};

    Class[] payload() default {};
}
