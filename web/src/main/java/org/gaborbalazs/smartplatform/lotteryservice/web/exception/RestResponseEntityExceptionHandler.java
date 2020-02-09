package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

import java.time.ZonedDateTime;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice(basePackages = {"org.gaborbalazs.smartplatform.lotteryservice"})
class RestResponseEntityExceptionHandler {

    private final RequestContext requestContext;

    RestResponseEntityExceptionHandler(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    ExceptionResponse handleIllegalArgumentException(Exception exception, WebRequest request) {
        return createExceptionResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(UnsupportedOperationException.class)
    ExceptionResponse handleUnsupportedOperationException(Exception exception, WebRequest webRequest) {
        return createExceptionResponse(exception, HttpStatus.NOT_IMPLEMENTED);
    }

    private ExceptionResponse createExceptionResponse(Exception exception, HttpStatus httpStatus) {
        return ExceptionResponse.newBuilder()
                .withTimestamp(ZonedDateTime.now())
                .withStatus(httpStatus.value())
                .withError(httpStatus.getReasonPhrase())
                .withMessage(exception.getMessage())
                .withConsumerName(requestContext.getConsumerName())
                .withRequestId(requestContext.getRequestId())
                .withPath(requestContext.getRequestURI())
                .build();
    }
}
