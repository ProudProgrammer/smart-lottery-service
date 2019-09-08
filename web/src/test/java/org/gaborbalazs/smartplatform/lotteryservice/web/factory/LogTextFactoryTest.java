package org.gaborbalazs.smartplatform.lotteryservice.web.factory;

import org.gaborbalazs.smartplatform.lotteryservice.web.wrapper.BufferedRequestWrapper;
import org.gaborbalazs.smartplatform.lotteryservice.web.wrapper.BufferedResponseWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class LogTextFactoryTest {

    private static final String REQUEST_URI = "test\\test";

    private static final String BASE_REQUEST = "Incoming request [uri=" + REQUEST_URI + "]";
    private static final String BASE_RESPONSE = "Outgoing response []";

    @InjectMocks
    private LogTextFactory underTest;

    @Mock
    private BufferedRequestWrapper bufferedRequestWrapper;

    @Mock
    private BufferedResponseWrapper bufferedResponseWrapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createRequestLogText() {
        // GIVEN
        underTest.setIncludeRequestQueryString(false);
        underTest.setIncludeRequestClientInfo(false);
        underTest.setIncludeRequestHeaders(false);
        underTest.setIncludeRequestPayload(false);
        Mockito.when(bufferedRequestWrapper.getRequestURI()).thenReturn(REQUEST_URI);

        // WHEN
        String result = underTest.createRequestLogText(bufferedRequestWrapper);

        // THEN
        Assertions.assertEquals(result, BASE_REQUEST);
    }

    @Test
    void createResponseLogText() {
        // GIVEN
        underTest.setIncludeResponseHeaders(false);
        underTest.setIncludeResponsePayload(false);

        // WHEN
        String result = underTest.createResponseLogText(bufferedResponseWrapper);

        // THEN
        Assertions.assertEquals(result, BASE_RESPONSE);
    }
}