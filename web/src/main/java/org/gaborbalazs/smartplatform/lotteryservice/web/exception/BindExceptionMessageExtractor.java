package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;

@Component
class BindExceptionMessageExtractor implements ExceptionMessageExtractor<BindException> {

    private final MessageResolver messageResolver;

    BindExceptionMessageExtractor(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    @Override
    public String extract(BindException exception) {
        String message;
        if (exception.hasErrors() && exception.getAllErrors().get(0) != null) {
            message = getErrorMessage(exception.getAllErrors().get(0));
        } else {
            message = messageResolver.withRequestLocale(MSG_INPUT_PARAMETER_NOT_APPROPRIATE);
        }
        return message;
    }

    private String getErrorMessage(ObjectError error) {
        String message;
        if (error.contains(IllegalArgumentException.class)) {
            message = error.unwrap(IllegalArgumentException.class).getMessage();
        } else if (error.contains(ConstraintViolation.class)) {
            ConstraintViolation<?> violation = error.unwrap(ConstraintViolation.class);
            message = violation.getPropertyPath() + " " + violation.getMessage();
        } else {
            message = messageResolver.withRequestLocale(MSG_INPUT_PARAMETER_NOT_APPROPRIATE);
        }
        return message;
    }
}
