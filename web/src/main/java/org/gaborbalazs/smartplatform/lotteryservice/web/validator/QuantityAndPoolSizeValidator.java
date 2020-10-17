package org.gaborbalazs.smartplatform.lotteryservice.web.validator;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.QuantityAndPoolSize;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

@Component
public class QuantityAndPoolSizeValidator implements ConstraintValidator<ValidQuantityAndPoolSize, QuantityAndPoolSize> {

    private static final String MSG_QUANTITY_MUST_NOT_BE_0 = "validate.generator.quantityMustNotBe0";
    private static final String MSG_POOL_SIZE_MUST_NOT_BE_0 = "validate.generator.poolSizeMustNotBe0";
    private static final String MSG_POOL_SIZE_MUST_NOT_BE_LARGER_THAN = "validate.generator.poolSizeMustNotBeLargerThan";
    private static final String MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY = "validate.generator.poolSizeMustBeLargerThanQuantity";

    private final MessageSource messageSource;
    private final RequestContext requestContext;
    private final Logger logger;

    QuantityAndPoolSizeValidator(MessageSource messageSource, RequestContext requestContext, Logger logger) {
        this.messageSource = messageSource;
        this.requestContext = requestContext;
        this.logger = logger;
    }

    @Override
    public void initialize(ValidQuantityAndPoolSize constraintAnnotation) {
    }

    @Override
    public boolean isValid(QuantityAndPoolSize quantityAndPoolSize, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        if (quantityAndPoolSize.getQuantity() == 0) {
            logger.error(messageSource.getMessage(MSG_QUANTITY_MUST_NOT_BE_0, null, Locale.US));
            setConstraintViolationMessage(constraintValidatorContext, messageSource.getMessage(MSG_QUANTITY_MUST_NOT_BE_0, null, requestContext.getLocale()));
            result = false;
        } else if (quantityAndPoolSize.getPoolSize() == 0) {
            logger.error(messageSource.getMessage(MSG_POOL_SIZE_MUST_NOT_BE_0, null, Locale.US));
            setConstraintViolationMessage(constraintValidatorContext, messageSource.getMessage(MSG_POOL_SIZE_MUST_NOT_BE_0, null, requestContext.getLocale()));
            result = false;
        } else if (quantityAndPoolSize.getPoolSize() > 1000) {
            logger.error(messageSource.getMessage(MSG_POOL_SIZE_MUST_NOT_BE_LARGER_THAN, new Object[]{1000, quantityAndPoolSize.getPoolSize()}, Locale.US));
            setConstraintViolationMessage(constraintValidatorContext, messageSource.getMessage(MSG_POOL_SIZE_MUST_NOT_BE_LARGER_THAN, new Object[]{1000, quantityAndPoolSize.getPoolSize()}, requestContext.getLocale()));
            result = false;
        } else if (quantityAndPoolSize.getPoolSize() <= quantityAndPoolSize.getQuantity()) {
            logger.error(messageSource.getMessage(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, new Object[]{quantityAndPoolSize.getQuantity(), quantityAndPoolSize.getPoolSize()}, Locale.US));
            setConstraintViolationMessage(constraintValidatorContext, messageSource.getMessage(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, new Object[]{quantityAndPoolSize.getQuantity(), quantityAndPoolSize.getPoolSize()}, requestContext.getLocale()));
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    private void setConstraintViolationMessage(ConstraintValidatorContext constraintValidatorContext, String message) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
