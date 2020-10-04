package org.gaborbalazs.smartplatform.lotteryservice.service.generator.validator;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ExperimentalLotteryNumberGeneratorValidator {

    private static final String MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY = "validate.generator.poolSizeMustBeLargerThanQuantity";
    private static final String MSG_ONLY_QUANTITY_5_POOL_SIZE_90_SUPPORTED = "validate.generator.onlyQuantity5PoolSize90Supported";
    private static final String MSG_UNSUPPORTED_OPERATION = "validate.generator.unsupportedOperation";

    private final MessageSource messageSource;
    private final RequestContext requestContext;
    private final Logger logger;

    public ExperimentalLotteryNumberGeneratorValidator(MessageSource messageSource, RequestContext requestContext, Logger logger) {
        this.messageSource = messageSource;
        this.requestContext = requestContext;
        this.logger = logger;
    }

    public void validate(int quantity, int poolSize) throws IllegalArgumentException, UnsupportedOperationException {
        if (poolSize <= quantity) {
            logger.error(messageSource.getMessage(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, new Object[]{quantity, poolSize}, Locale.US));
            throw new IllegalArgumentException(messageSource.getMessage(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, new Object[]{quantity, poolSize}, requestContext.getLocale()));
        } else if (!(quantity == 5 && poolSize == 90)) {
            logger.error(messageSource.getMessage(MSG_ONLY_QUANTITY_5_POOL_SIZE_90_SUPPORTED, new Object[]{quantity, poolSize}, Locale.US));
            throw new UnsupportedOperationException(messageSource.getMessage(MSG_ONLY_QUANTITY_5_POOL_SIZE_90_SUPPORTED, new Object[]{quantity, poolSize}, requestContext.getLocale()));
        }
    }

    public UnsupportedOperationException unsupportedOperation() {
        logger.error(messageSource.getMessage(MSG_UNSUPPORTED_OPERATION, null, Locale.US));
        return new UnsupportedOperationException(messageSource.getMessage(MSG_UNSUPPORTED_OPERATION, null, requestContext.getLocale()));
    }
}
