package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
class MethodArgumentNotValidExceptionMessageExtractor implements ExceptionMessageExtractor<MethodArgumentNotValidException> {

    private final MessageResolver messageResolver;

    MethodArgumentNotValidExceptionMessageExtractor(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    @Override
    public String extract(MethodArgumentNotValidException exception) {
        String message;
        if (exception.getBindingResult().hasErrors()) {
            message = exception.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        } else {
            message = messageResolver.withRequestLocale(MSG_INPUT_PARAMETER_NOT_APPROPRIATE);
        }
        return message;
    }
}
