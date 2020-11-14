package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;

@Component
class ConstraintViolationExceptionMessageExtractor {

    private final String MSG_INPUT_PARAMETER_NOT_APPROPRIATE = "validate.generator.inputParameterNotAppropriate";

    private final MessageResolver messageResolver;

    public ConstraintViolationExceptionMessageExtractor(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    String extract(ConstraintViolationException exception) {
        String message;
        if (exception.getConstraintViolations().stream().findFirst().isPresent()) {
            message = exception.getConstraintViolations().stream().findFirst().get().getMessage();
        } else {
            message = messageResolver.withRequestLocale(MSG_INPUT_PARAMETER_NOT_APPROPRIATE);
        }
        return message;
    }
}
