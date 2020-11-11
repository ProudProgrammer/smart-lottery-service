package org.gaborbalazs.smartplatform.lotteryservice.web.validator;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.LotteryTypeGeneratorTypeRequest;
import org.slf4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LotteryTypeGeneratorTypeRequestValidator implements ConstraintValidator<ValidLotteryTypeGeneratorTypeRequest, LotteryTypeGeneratorTypeRequest> {

    private static final String MSG_ONLY_FIVE_OUT_OF_NINETY_LOTTERY_TYPE_SUPPORTED = "validate.generator.onlyFiveOutOfNinetyLotteryTypeSupported";

    private final MessageResolver messageResolver;
    private final Logger logger;

    public LotteryTypeGeneratorTypeRequestValidator(MessageResolver messageResolver, Logger logger) {
        this.messageResolver = messageResolver;
        this.logger = logger;
    }

    @Override
    public void initialize(ValidLotteryTypeGeneratorTypeRequest constraintAnnotation) {
    }

    @Override
    public boolean isValid(LotteryTypeGeneratorTypeRequest request, ConstraintValidatorContext context) {
        boolean result = true;
        if (request.getGeneratorType() == GeneratorType.EXPERIMENTAL && request.getLotteryType() != LotteryType.FIVE_OUT_OF_NINETY) {
            logger.error(messageResolver.withUSLocale(MSG_ONLY_FIVE_OUT_OF_NINETY_LOTTERY_TYPE_SUPPORTED));
            setConstraintViolationMessage(context, messageResolver.withRequestLocale(MSG_ONLY_FIVE_OUT_OF_NINETY_LOTTERY_TYPE_SUPPORTED));
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
