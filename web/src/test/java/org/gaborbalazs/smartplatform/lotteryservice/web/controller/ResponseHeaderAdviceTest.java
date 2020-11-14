package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpResponse;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResponseHeaderAdviceTest {

    private static final String REQUEST_ID = "request id";
    private static final String CONSUMER_NAME = "consumer name";
    private static final Locale LOCALE = Locale.US;
    private static final String UNIDENTIFIED = "unidentified";

    @InjectMocks
    private ResponseHeaderAdvice underTest;

    @Mock
    private RequestContext requestContext;

    @Test
    void testShouldSetResponseHeadersFromRequestContextWhenThoseAreNotNulls() {
        // GIVEN
        ServerHttpResponse response = mock(ServerHttpResponse.class);
        HttpHeaders httpHeaders = mock(HttpHeaders.class);
        when(response.getHeaders()).thenReturn(httpHeaders);
        when(requestContext.getRequestId()).thenReturn(REQUEST_ID);
        when(requestContext.getConsumerName()).thenReturn(CONSUMER_NAME);
        when(requestContext.getLocale()).thenReturn(LOCALE);

        // WHEN
        underTest.beforeBodyWrite(null, null, null, null, null, response);

        // THEN
        verify(response, times(3)).getHeaders();
        verify(httpHeaders).add(HeaderParameterName.REQUEST_ID.getValue(), REQUEST_ID);
        verify(httpHeaders).add(HeaderParameterName.CONSUMER_NAME.getValue(), CONSUMER_NAME);
        verify(httpHeaders).add(HeaderParameterName.LOCALE.getValue(), LOCALE.toString());
    }

    @Test
    void testShouldSetResponseHeadersWithDefaultTextWhenRequestContextFieldsAreNulls() {
        // GIVEN
        ServerHttpResponse response = mock(ServerHttpResponse.class);
        HttpHeaders httpHeaders = mock(HttpHeaders.class);
        when(response.getHeaders()).thenReturn(httpHeaders);
        when(requestContext.getRequestId()).thenReturn(null);
        when(requestContext.getConsumerName()).thenReturn(null);
        when(requestContext.getLocale()).thenReturn(null);

        // WHEN
        underTest.beforeBodyWrite(null, null, null, null, null, response);

        // THEN
        verify(response, times(3)).getHeaders();
        verify(httpHeaders).add(HeaderParameterName.REQUEST_ID.getValue(), UNIDENTIFIED);
        verify(httpHeaders).add(HeaderParameterName.CONSUMER_NAME.getValue(), UNIDENTIFIED);
        verify(httpHeaders).add(HeaderParameterName.LOCALE.getValue(), UNIDENTIFIED);
    }
}