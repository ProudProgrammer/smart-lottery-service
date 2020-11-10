package org.gaborbalazs.smartplatform.lotteryservice.web.validator;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.web.component.MessageResolver;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.QuantityPoolSizeGeneratorTypeRequest;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class QuantityPoolSizeGeneratorTypeRequestValidator implements ConstraintValidator<ValidQuantityPoolSizeGeneratorTypeRequest, QuantityPoolSizeGeneratorTypeRequest> {

    private static final String MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY = "validate.generator.poolSizeMustBeLargerThanQuantity";
    private static final String MSG_ONLY_QUANTITY_5_POOL_SIZE_90_SUPPORTED = "validate.generator.onlyQuantity5PoolSize90Supported";

    private final MessageResolver messageResolver;
    private final Logger logger;

    public QuantityPoolSizeGeneratorTypeRequestValidator(MessageResolver messageResolver, Logger logger) {
        this.messageResolver = messageResolver;
        this.logger = logger;
    }

    @Override
    public void initialize(ValidQuantityPoolSizeGeneratorTypeRequest constraintAnnotation) {
    }

    @Override
    public boolean isValid(QuantityPoolSizeGeneratorTypeRequest request, ConstraintValidatorContext context) {
        boolean result = true;
        if (request.getPoolSize() <= request.getQuantity()) {
            logger.error(messageResolver.withUSLocale(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, request.getQuantity(), request.getPoolSize()));
            setConstraintViolationMessage(context, messageResolver.withRequestLocale(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, request.getQuantity(), request.getPoolSize()));
            result = false;
        }
        if (request.getGeneratorType() == GeneratorType.EXPERIMENTAL && !(request.getQuantity() == 5 && request.getPoolSize() == 90)) {
            logger.error(messageResolver.withUSLocale(MSG_ONLY_QUANTITY_5_POOL_SIZE_90_SUPPORTED, request.getQuantity(), request.getPoolSize()));
            setConstraintViolationMessage(context, messageResolver.withRequestLocale(MSG_ONLY_QUANTITY_5_POOL_SIZE_90_SUPPORTED, request.getQuantity(), request.getPoolSize()));
            result = false;
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
