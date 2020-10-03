package org.gaborbalazs.smartplatform.lotteryservice.web.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RequestIdFactoryTest {

    private static final String PATTERN_UUID = "[a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8}";

    @InjectMocks
    private RequestIdFactory underTest;

    @Mock
    private Logger logger;

    @Test
    void testShouldReturnAValidGeneratedUUIDWhenInputIsNull() {
        // GIVEN
        String requestId = null;

        // WHEN
        String result = underTest.create(requestId);

        // THEN
        verify(logger).debug(anyString());
        assertTrue(result.matches(PATTERN_UUID));
    }

    @Test
    void testShouldReturnAValidGeneratedUUIDWhenInputIsBlank() {
        // GIVEN
        String requestId = "";

        // WHEN
        String result = underTest.create(requestId);

        // THEN
        verify(logger).debug(anyString());
        assertTrue(result.matches(PATTERN_UUID));
    }

    @Test
    void testShouldReturnWithTheInputWhenInputIsNotNull() {
        // GIVEN
        String requestId = "requestId";

        // WHEN
        String result = underTest.create(requestId);

        // THEN
        verify(logger).debug(anyString());
        assertEquals(requestId, result);
    }
}