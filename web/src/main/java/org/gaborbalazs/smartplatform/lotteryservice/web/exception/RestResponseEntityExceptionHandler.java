package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;

@RestControllerAdvice(basePackages = "org.gaborbalazs.smartplatform.lotteryservice")
class RestResponseEntityExceptionHandler {

    private final String MSG_INPUT_PARAMETER_NOT_APPROPRIATE = "validate.generator.inputParameterNotAppropriate";

    private final RequestContext requestContext;
    private final MessageResolver messageResolver;

    public RestResponseEntityExceptionHandler(RequestContext requestContext, MessageResolver messageResolver) {
        this.requestContext = requestContext;
        this.messageResolver = messageResolver;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    ExceptionResponse handleBindExceptionException(BindException exception) {
        return createExceptionResponse(getErrorMessage(exception), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ExceptionResponse handleConstraintViolationException(ConstraintViolationException exception) {
        return createExceptionResponse(getConstraintViolationMessage(exception), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    ExceptionResponse handleIllegalArgumentException(IllegalArgumentException exception) {
        return createExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(UnsupportedOperationException.class)
    ExceptionResponse handleUnsupportedOperationException(UnsupportedOperationException exception) {
        return createExceptionResponse(exception.getMessage(), HttpStatus.NOT_IMPLEMENTED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RestClientException.class)
    ExceptionResponse handleRestClientException(RestClientException exception) {
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
            message = messageResolver.withRequestLocale(MSG_INPUT_PARAMETER_NOT_APPROPRIATE);
        }
        return message;
    }

    private String getErrorMessage(BindException exception) {
        String message;
        if (exception.hasErrors() && exception.getAllErrors().stream().findFirst().isPresent()) {
            message = exception.getAllErrors().stream().findFirst().get().getDefaultMessage();
        } else {
            message = messageResolver.withRequestLocale(MSG_INPUT_PARAMETER_NOT_APPROPRIATE);
        }
        return message;
    }
}
