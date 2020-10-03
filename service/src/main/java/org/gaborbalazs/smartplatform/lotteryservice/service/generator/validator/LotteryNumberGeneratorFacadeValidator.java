package org.gaborbalazs.smartplatform.lotteryservice.service.generator.validator;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LotteryNumberGeneratorFacadeValidator {

    private static final String MSG_QUANTITY_MUST_NOT_BE_0 = "validate.generator.quantityMustNotBe0";
    private static final String MSG_POOL_SIZE_MUST_NOT_BE_LARGER_THAN = "validate.generator.poolSizeMustNotBeLargerThan";
    private static final String MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY = "validate.generator.poolSizeMustBeLargerThanQuantity";

    private final MessageSource messageSource;
    private final RequestContext requestContext;
    private final Logger logger;

    LotteryNumberGeneratorFacadeValidator(MessageSource messageSource, RequestContext requestContext, Logger logger) {
        this.messageSource = messageSource;
        this.requestContext = requestContext;
        this.logger = logger;
    }

    public void validate(int quantity, int poolSize) throws IllegalArgumentException {
        if (quantity == 0) {
            logger.error(messageSource.getMessage(MSG_QUANTITY_MUST_NOT_BE_0, null, Locale.US));
            throw new IllegalArgumentException(messageSource.getMessage(MSG_QUANTITY_MUST_NOT_BE_0, null, requestContext.getLocale()));
        } else if (poolSize > 1000) {
            logger.error(messageSource.getMessage(MSG_POOL_SIZE_MUST_NOT_BE_LARGER_THAN, new Object[]{1000, poolSize}, Locale.US));
            throw new IllegalArgumentException(messageSource.getMessage(MSG_POOL_SIZE_MUST_NOT_BE_LARGER_THAN, new Object[]{1000, poolSize}, requestContext.getLocale()));
        } else if (poolSize <= quantity) {
            logger.error(messageSource.getMessage(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, new Object[]{quantity, poolSize}, Locale.US));
            throw new IllegalArgumentException(messageSource.getMessage(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, new Object[]{quantity, poolSize}, requestContext.getLocale()));
        }
    }
}
