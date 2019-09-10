package org.gaborbalazs.smartplatform.lotteryservice.web.factory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.gaborbalazs.smartplatform.lotteryservice.web.wrapper.BufferedRequestWrapper;
import org.gaborbalazs.smartplatform.lotteryservice.web.wrapper.BufferedResponseWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LogTextFactoryTest {

    private static final String METHOD = "POST";
    private static final String REQUEST_URI = "test\\test";
    private static final String HEADER_NAME_1 = "header1";
    private static final String HEADER_NAME_2 = "header2";
    private static final String HEADER_NAME_3 = "header3";
    private static final String HEADER_VALUE_1 = "value1";
    private static final String HEADER_VALUE_2 = "value2";
    private static final String HEADER_VALUE_3 = "value3";
    private static final Collection<String> HEADER_NAMES_WITH_THREE_ELEMENTS = List.of(HEADER_NAME_1, HEADER_NAME_2, HEADER_NAME_3);
    private static final Collection<String> HEADER_NAMES_WITH_ONE_ELEMENT = List.of(HEADER_NAME_1);
    private static final Collection<String> HEADER_NAMES_WITHOUT_ELEMENT = Collections.emptyList();
    private static final String PAYLOAD = "{\"name1\":\"value1\",\"name2\":\"value2\"}";
    private static final String QUERY_STRING = "name1=value1&name2=value2";
    private static final String REMOTE_ADDRESS = "remote.address";

    private static final String BASE_REQUEST = "Incoming request [method=POST; uri=test\\test]";
    private static final String FULL_REQUEST = "Incoming request [method=POST; uri=test\\test; query=[name1=value1&name2=value2]; client=remote.address; "
            + "headers=[header1:\"value1\", header2:\"value2\", header3:\"value3\"]; payload={\"name1\":\"value1\",\"name2\":\"value2\"}]";
    private static final String QUERY_STRING_REQUEST = "Incoming request [method=POST; uri=test\\test; query=[name1=value1&name2=value2]]";
    private static final String EMPTY_QUERY_STRING_REQUEST = "Incoming request [method=POST; uri=test\\test; query=[]]";
    private static final String CLIENT_INFO_REQUEST = "Incoming request [method=POST; uri=test\\test; client=remote.address]";
    private static final String HEADER_REQUEST_WITHOUT_ELEMENT = "Incoming request [method=POST; uri=test\\test; headers=[]]";
    private static final String HEADER_REQUEST_WITH_ONE_ELEMENT = "Incoming request [method=POST; uri=test\\test; headers=[header1:\"value1\"]]";
    private static final String HEADER_REQUEST_WITH_THREE_ELEMENTS = "Incoming request [method=POST; uri=test\\test; headers=[header1:\"value1\", header2:\"value2\", header3:\"value3\"]]";
    private static final String PAYLOAD_REQUEST = "Incoming request [method=POST; uri=test\\test; payload={\"name1\":\"value1\",\"name2\":\"value2\"}]";

    private static final String BASE_RESPONSE = "Outgoing response []";
    private static final String FULL_RESPONSE = "Outgoing response [headers=[header1:\"value1\", header2:\"value2\", header3:\"value3\"]; "
            + "payload={\"name1\":\"value1\",\"name2\":\"value2\"}]";
    private static final String HEADER_RESPONSE_WITHOUT_ELEMENT = "Outgoing response [headers=[]]";
    private static final String HEADER_RESPONSE_WITH_ONE_ELEMENT = "Outgoing response [headers=[header1:\"value1\"]]";
    private static final String HEADER_RESPONSE_WITH_THREE_ELEMENTS = "Outgoing response [headers=[header1:\"value1\", header2:\"value2\", header3:\"value3\"]]";
    private static final String PAYLOAD_RESPONSE = "Outgoing response [payload={\"name1\":\"value1\",\"name2\":\"value2\"}]";

    @InjectMocks
    private LogTextFactory underTest;

    @Mock
    private BufferedRequestWrapper bufferedRequestWrapper;

    @Mock
    private BufferedResponseWrapper bufferedResponseWrapper;

    @Test
    void testCreateRequestLogText() {
        // GIVEN
        underTest.setIncludeRequestQueryString(false);
        underTest.setIncludeRequestClientInfo(false);
        underTest.setIncludeRequestHeaders(false);
        underTest.setIncludeRequestPayload(false);
        Mockito.when(bufferedRequestWrapper.getMethod()).thenReturn(METHOD);
        Mockito.when(bufferedRequestWrapper.getRequestURI()).thenReturn(REQUEST_URI);

        // WHEN
        String result = underTest.createRequestLogText(bufferedRequestWrapper);

        // THEN
        Assertions.assertEquals(BASE_REQUEST, result);
    }

    @Test
    void testCreateRequestLogTextWhenQueryStringAndClientInfoAndHeadersAndPayloadIncluded() {
        // GIVEN
        underTest.setIncludeRequestQueryString(true);
        underTest.setIncludeRequestClientInfo(true);
        underTest.setIncludeRequestHeaders(true);
        underTest.setIncludeRequestPayload(true);
        Mockito.when(bufferedRequestWrapper.getMethod()).thenReturn(METHOD);
        Mockito.when(bufferedRequestWrapper.getRequestURI()).thenReturn(REQUEST_URI);
        Mockito.when(bufferedRequestWrapper.getQueryString()).thenReturn(QUERY_STRING);
        Mockito.when(bufferedRequestWrapper.getRemoteAddr()).thenReturn(REMOTE_ADDRESS);
        Mockito.when(bufferedRequestWrapper.getHeaderNames()).thenReturn(Collections.enumeration(HEADER_NAMES_WITH_THREE_ELEMENTS));
        Mockito.when(bufferedRequestWrapper.getHeader(HEADER_NAME_1)).thenReturn(HEADER_VALUE_1);
        Mockito.when(bufferedRequestWrapper.getHeader(HEADER_NAME_2)).thenReturn(HEADER_VALUE_2);
        Mockito.when(bufferedRequestWrapper.getHeader(HEADER_NAME_3)).thenReturn(HEADER_VALUE_3);
        Mockito.when(bufferedRequestWrapper.getBody()).thenReturn(PAYLOAD);

        // WHEN
        String result = underTest.createRequestLogText(bufferedRequestWrapper);

        // THEN
        Assertions.assertEquals(FULL_REQUEST, result);
    }

    @Test
    void testCreateRequestLogTextWhenQueryStringIncluded() {
        // GIVEN
        underTest.setIncludeRequestQueryString(true);
        underTest.setIncludeRequestClientInfo(false);
        underTest.setIncludeRequestHeaders(false);
        underTest.setIncludeRequestPayload(false);
        Mockito.when(bufferedRequestWrapper.getMethod()).thenReturn(METHOD);
        Mockito.when(bufferedRequestWrapper.getRequestURI()).thenReturn(REQUEST_URI);
        Mockito.when(bufferedRequestWrapper.getQueryString()).thenReturn(QUERY_STRING);

        // WHEN
        String result = underTest.createRequestLogText(bufferedRequestWrapper);

        // THEN
        Assertions.assertEquals(QUERY_STRING_REQUEST, result);
    }

    @Test
    void testCreateRequestLogTextWhenEmptyQueryStringIncluded() {
        // GIVEN
        underTest.setIncludeRequestQueryString(true);
        underTest.setIncludeRequestClientInfo(false);
        underTest.setIncludeRequestHeaders(false);
        underTest.setIncludeRequestPayload(false);
        Mockito.when(bufferedRequestWrapper.getMethod()).thenReturn(METHOD);
        Mockito.when(bufferedRequestWrapper.getRequestURI()).thenReturn(REQUEST_URI);
        Mockito.when(bufferedRequestWrapper.getQueryString()).thenReturn(null);

        // WHEN
        String result = underTest.createRequestLogText(bufferedRequestWrapper);

        // THEN
        Assertions.assertEquals(EMPTY_QUERY_STRING_REQUEST, result);
    }

    @Test
    void testCreateRequestLogTextWhenClientInfoIncluded() {
        // GIVEN
        underTest.setIncludeRequestQueryString(false);
        underTest.setIncludeRequestClientInfo(true);
        underTest.setIncludeRequestHeaders(false);
        underTest.setIncludeRequestPayload(false);
        Mockito.when(bufferedRequestWrapper.getMethod()).thenReturn(METHOD);
        Mockito.when(bufferedRequestWrapper.getRequestURI()).thenReturn(REQUEST_URI);
        Mockito.when(bufferedRequestWrapper.getRemoteAddr()).thenReturn(REMOTE_ADDRESS);

        // WHEN
        String result = underTest.createRequestLogText(bufferedRequestWrapper);

        // THEN
        Assertions.assertEquals(CLIENT_INFO_REQUEST, result);
    }

    @Test
    void testCreateRequestLogTextWhenHeadersIncludedWithoutElement() {
        // GIVEN
        underTest.setIncludeRequestQueryString(false);
        underTest.setIncludeRequestClientInfo(false);
        underTest.setIncludeRequestHeaders(true);
        underTest.setIncludeRequestPayload(false);
        Mockito.when(bufferedRequestWrapper.getMethod()).thenReturn(METHOD);
        Mockito.when(bufferedRequestWrapper.getRequestURI()).thenReturn(REQUEST_URI);
        Mockito.when(bufferedRequestWrapper.getHeaderNames()).thenReturn(Collections.emptyEnumeration());

        // WHEN
        String result = underTest.createRequestLogText(bufferedRequestWrapper);

        // THEN
        Assertions.assertEquals(HEADER_REQUEST_WITHOUT_ELEMENT, result);
    }

    @Test
    void testCreateRequestLogTextWhenHeadersIncludedWithOneElement() {
        // GIVEN
        underTest.setIncludeRequestQueryString(false);
        underTest.setIncludeRequestClientInfo(false);
        underTest.setIncludeRequestHeaders(true);
        underTest.setIncludeRequestPayload(false);
        Mockito.when(bufferedRequestWrapper.getMethod()).thenReturn(METHOD);
        Mockito.when(bufferedRequestWrapper.getRequestURI()).thenReturn(REQUEST_URI);
        Mockito.when(bufferedRequestWrapper.getHeaderNames()).thenReturn(Collections.enumeration(HEADER_NAMES_WITH_ONE_ELEMENT));
        Mockito.when(bufferedRequestWrapper.getHeader(HEADER_NAME_1)).thenReturn(HEADER_VALUE_1);

        // WHEN
        String result = underTest.createRequestLogText(bufferedRequestWrapper);

        // THEN
        Assertions.assertEquals(HEADER_REQUEST_WITH_ONE_ELEMENT, result);
    }

    @Test
    void testCreateRequestLogTextWhenHeadersIncludedWithThreeElements() {
        // GIVEN
        underTest.setIncludeRequestQueryString(false);
        underTest.setIncludeRequestClientInfo(false);
        underTest.setIncludeRequestHeaders(true);
        underTest.setIncludeRequestPayload(false);
        Mockito.when(bufferedRequestWrapper.getMethod()).thenReturn(METHOD);
        Mockito.when(bufferedRequestWrapper.getRequestURI()).thenReturn(REQUEST_URI);
        Mockito.when(bufferedRequestWrapper.getHeaderNames()).thenReturn(Collections.enumeration(HEADER_NAMES_WITH_THREE_ELEMENTS));
        Mockito.when(bufferedRequestWrapper.getHeader(HEADER_NAME_1)).thenReturn(HEADER_VALUE_1);
        Mockito.when(bufferedRequestWrapper.getHeader(HEADER_NAME_2)).thenReturn(HEADER_VALUE_2);
        Mockito.when(bufferedRequestWrapper.getHeader(HEADER_NAME_3)).thenReturn(HEADER_VALUE_3);

        // WHEN
        String result = underTest.createRequestLogText(bufferedRequestWrapper);

        // THEN
        Assertions.assertEquals(HEADER_REQUEST_WITH_THREE_ELEMENTS, result);
    }

    @Test
    void testCreateRequestLogTextWhenPayloadIncluded() {
        // GIVEN
        underTest.setIncludeRequestQueryString(false);
        underTest.setIncludeRequestClientInfo(false);
        underTest.setIncludeRequestHeaders(false);
        underTest.setIncludeRequestPayload(true);
        Mockito.when(bufferedRequestWrapper.getMethod()).thenReturn(METHOD);
        Mockito.when(bufferedRequestWrapper.getRequestURI()).thenReturn(REQUEST_URI);
        Mockito.when(bufferedRequestWrapper.getBody()).thenReturn(PAYLOAD);

        // WHEN
        String result = underTest.createRequestLogText(bufferedRequestWrapper);

        // THEN
        Assertions.assertEquals(PAYLOAD_REQUEST, result);
    }

    @Test
    void testCreateResponseLogText() {
        // GIVEN
        underTest.setIncludeResponseHeaders(false);
        underTest.setIncludeResponsePayload(false);

        // WHEN
        String result = underTest.createResponseLogText(bufferedResponseWrapper);

        // THEN
        Assertions.assertEquals(BASE_RESPONSE, result);
    }

    @Test
    void testCreateResponseLogTextWhenHeadersAndPayloadIncluded() {
        // GIVEN
        underTest.setIncludeResponseHeaders(true);
        underTest.setIncludeResponsePayload(true);
        Mockito.when(bufferedResponseWrapper.getHeaderNames()).thenReturn(HEADER_NAMES_WITH_THREE_ELEMENTS);
        Mockito.when(bufferedResponseWrapper.getHeader(HEADER_NAME_1)).thenReturn(HEADER_VALUE_1);
        Mockito.when(bufferedResponseWrapper.getHeader(HEADER_NAME_2)).thenReturn(HEADER_VALUE_2);
        Mockito.when(bufferedResponseWrapper.getHeader(HEADER_NAME_3)).thenReturn(HEADER_VALUE_3);
        Mockito.when(bufferedResponseWrapper.getBody()).thenReturn(PAYLOAD);

        // WHEN
        String result = underTest.createResponseLogText(bufferedResponseWrapper);

        // THEN
        Assertions.assertEquals(FULL_RESPONSE, result);
    }

    @Test
    void testCreateResponseLogTextWhenHeadersIncludedWithoutElement() {
        // GIVEN
        underTest.setIncludeResponseHeaders(true);
        underTest.setIncludeResponsePayload(false);
        Mockito.when(bufferedResponseWrapper.getHeaderNames()).thenReturn(HEADER_NAMES_WITHOUT_ELEMENT);

        // WHEN
        String result = underTest.createResponseLogText(bufferedResponseWrapper);

        // THEN
        Assertions.assertEquals(HEADER_RESPONSE_WITHOUT_ELEMENT, result);
    }

    @Test
    void testCreateResponseLogTextWhenHeadersIncludedWithOneElement() {
        // GIVEN
        underTest.setIncludeResponseHeaders(true);
        underTest.setIncludeResponsePayload(false);
        Mockito.when(bufferedResponseWrapper.getHeaderNames()).thenReturn(HEADER_NAMES_WITH_ONE_ELEMENT);
        Mockito.when(bufferedResponseWrapper.getHeader(HEADER_NAME_1)).thenReturn(HEADER_VALUE_1);

        // WHEN
        String result = underTest.createResponseLogText(bufferedResponseWrapper);

        // THEN
        Assertions.assertEquals(HEADER_RESPONSE_WITH_ONE_ELEMENT, result);
    }

    @Test
    void testCreateResponseLogTextWhenHeadersIncludedWithThreeElements() {
        // GIVEN
        underTest.setIncludeResponseHeaders(true);
        underTest.setIncludeResponsePayload(false);
        Mockito.when(bufferedResponseWrapper.getHeaderNames()).thenReturn(HEADER_NAMES_WITH_THREE_ELEMENTS);
        Mockito.when(bufferedResponseWrapper.getHeader(HEADER_NAME_1)).thenReturn(HEADER_VALUE_1);
        Mockito.when(bufferedResponseWrapper.getHeader(HEADER_NAME_2)).thenReturn(HEADER_VALUE_2);
        Mockito.when(bufferedResponseWrapper.getHeader(HEADER_NAME_3)).thenReturn(HEADER_VALUE_3);

        // WHEN
        String result = underTest.createResponseLogText(bufferedResponseWrapper);

        // THEN
        Assertions.assertEquals(HEADER_RESPONSE_WITH_THREE_ELEMENTS, result);
    }

    @Test
    void testCreateResponseLogTextWhenPayloadIncluded() {
        // GIVEN
        underTest.setIncludeResponseHeaders(false);
        underTest.setIncludeResponsePayload(true);
        Mockito.when(bufferedResponseWrapper.getBody()).thenReturn(PAYLOAD);

        // WHEN
        String result = underTest.createResponseLogText(bufferedResponseWrapper);

        // THEN
        Assertions.assertEquals(PAYLOAD_RESPONSE, result);
    }
}