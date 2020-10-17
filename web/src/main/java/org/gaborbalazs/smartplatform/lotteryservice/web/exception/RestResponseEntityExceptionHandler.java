package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;

@RestControllerAdvice(basePackages = {"org.gaborbalazs.smartplatform.lotteryservice"})
class RestResponseEntityExceptionHandler {

    private final String MSG_INPUT_PARAMETER_NOT_APPROPRIATE = "validate.generator.inputParameterNotAppropriate";

    private final RequestContext requestContext;
    private final MessageSource messageSource;

    RestResponseEntityExceptionHandler(RequestContext requestContext, MessageSource messageSource) {
        this.requestContext = requestContext;
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ExceptionResponse handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        return createExceptionResponse(getConstraintViolationMessage(exception), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    ExceptionResponse handleIllegalArgumentException(Exception exception, WebRequest request) {
        return createExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(UnsupportedOperationException.class)
    ExceptionResponse handleUnsupportedOperationException(Exception exception, WebRequest request) {
        return createExceptionResponse(exception.getMessage(), HttpStatus.NOT_IMPLEMENTED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RestClientException.class)
    ExceptionResponse handleLotteryNumberGeneratorClientException(Exception exception, WebRequest request) {
        return createExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ExceptionResponse createExceptionResponse(String message, HttpStatus httpStatus) {
        return ExceptionResponse.newBuilder()
                .withTimestamp(ZonedDateTime.now())
                .withStatus(httpStatus.value())
                .withError(httpStatus.getReasonPhrase())
                .withMessage(message)
                .withPath(requestContext.getRequestURI())
                .withQuery(requestContext.getRequestQuery())
                .build();
    }

    private String getConstraintViolationMessage(ConstraintViolationException exception) {
        String message;
        if (exception.getConstraintViolations().stream().findFirst().isPresent()) {
            message = exception.getConstraintViolations().stream().findFirst().get().getMessage();
        } else {
            message = resolveMessage(MSG_INPUT_PARAMETER_NOT_APPROPRIATE);
        }
        return message;
    }

    private String resolveMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, requestContext.getLocale());
    }
}
