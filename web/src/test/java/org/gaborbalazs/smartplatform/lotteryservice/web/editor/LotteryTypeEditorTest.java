package org.gaborbalazs.smartplatform.lotteryservice.web.editor;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LotteryTypeEditorTest {

    private static final String MSG_PATH_VARIABLE_CANNOT_BE_CONVERTED = "convert.pathVariableCannotBeConvertedToLotteryType";
    private static final String RESOLVED_MESSAGE = "resolved message";

    @InjectMocks
    private LotteryTypeEditor underTest;

    @Mock
    private MessageResolver messageResolver;

    @Mock
    private Logger logger;

    @Test
    void testShouldSetValueWhenPathVariableCanBeConverted() {
        // GIVEN
        String pathVariable = LotteryType.FIVE_OUT_OF_NINETY.getPathVariableName();

        // WHEN
        underTest.setAsText(pathVariable);

        // THEN
        assertEquals(LotteryType.FIVE_OUT_OF_NINETY, underTest.getValue());
    }

    @Test
    void testShouldThrowExceptionWhenPathVariableCannotBeConverted() {
        // GIVEN
        String pathVariable = "path variable";
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        Mockito.when(messageResolver.withUSLocale(MSG_PATH_VARIABLE_CANNOT_BE_CONVERTED, pathVariable)).thenReturn(RESOLVED_MESSAGE);

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.setAsText(pathVariable));
        verify(messageResolver).withUSLocale(MSG_PATH_VARIABLE_CANNOT_BE_CONVERTED, pathVariable);
        verify(logger).error(RESOLVED_MESSAGE);
        verify(messageResolver).withRequestLocale(MSG_PATH_VARIABLE_CANNOT_BE_CONVERTED, pathVariable);
    }
}