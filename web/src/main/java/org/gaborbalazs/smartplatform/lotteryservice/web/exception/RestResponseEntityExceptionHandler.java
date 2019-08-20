package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

import java.time.ZonedDateTime;

import org.gaborbalazs.smartplatform.lotteryservice.common.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"org.gaborbalazs.smartplatform.lotteryservice"})
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private RequestContext requestContext;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ExceptionResponse handleIllegalArgumentException(Exception exception, WebRequest request) {
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
