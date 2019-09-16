package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

import java.time.ZonedDateTime;

import org.gaborbalazs.smartplatform.lotteryservice.common.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice(basePackages = {"org.gaborbalazs.smartplatform.lotteryservice"})
class RestResponseEntityExceptionHandler {

    @Autowired
    private RequestContext requestContext;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    ExceptionResponse handleIllegalArgumentException(Exception exception, WebRequest request) {
        return createExceptionResponse(exception);
    }

    private ExceptionResponse createExceptionResponse(Exception exception) {
        return ExceptionResponse.newBuilder()
                .withTimestamp(ZonedDateTime.now())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .withMessage(exception.getMessage())
                .withConsumerName(requestContext.getConsumerName())
                .withRequestId(requestContext.getRequestId())
                .withPath(requestContext.getRequestURI())
                .build();
    }
}
