package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
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

    private final RequestContext requestContext;
    private final ExceptionMessageExtractorProvider exceptionMessageExtractorProvider;

    RestResponseEntityExceptionHandler(RequestContext requestContext, ExceptionMessageExtractorProvider exceptionMessageExtractorProvider) {
        this.requestContext = requestContext;
        this.exceptionMessageExtractorProvider = exceptionMessageExtractorProvider;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    ExceptionResponse handleBindException(BindException exception) {
        return createExceptionResponse(getMessage(exception), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ExceptionResponse handleConstraintViolationException(ConstraintViolationException exception) {
        return createExceptionResponse(getMessage(exception), HttpStatus.BAD_REQUEST);
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

    private String getMessage(Exception exception) {
        return exceptionMessageExtractorProvider.get(exception).extract(exception);
    }
}
