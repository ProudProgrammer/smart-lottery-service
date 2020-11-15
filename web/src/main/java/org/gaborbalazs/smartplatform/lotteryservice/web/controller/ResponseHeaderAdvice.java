package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@ControllerAdvice
class ResponseHeaderAdvice implements ResponseBodyAdvice<Object> {

    private static final String UNIDENTIFIED = "unidentified";

    private final RequestContext requestContext;

    ResponseHeaderAdvice(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        response.getHeaders().add(HeaderParameterName.REQUEST_ID.getValue(), Objects.requireNonNullElse(requestContext.getRequestId(), UNIDENTIFIED));
        response.getHeaders().add(HeaderParameterName.CONSUMER_NAME.getValue(), Objects.requireNonNullElse(requestContext.getConsumerName(), UNIDENTIFIED));
        response.getHeaders().add(HeaderParameterName.LOCALE.getValue(), Objects.requireNonNullElse(requestContext.getLocale(), UNIDENTIFIED).toString());
        return body;
    }
}
