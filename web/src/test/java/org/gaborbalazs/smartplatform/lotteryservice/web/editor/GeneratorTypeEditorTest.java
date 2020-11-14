package org.gaborbalazs.smartplatform.lotteryservice.web.editor;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
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
class GeneratorTypeEditorTest {

    private static final String MSG_REQUEST_PARAM_CANNOT_BE_CONVERTED = "convert.requestParamCannotBeConvertedToGeneratorType";
    private static final String RESOLVED_MESSAGE = "resolved message";

    @InjectMocks
    private GeneratorTypeEditor underTest;

    @Mock
    private MessageResolver messageResolver;

    @Mock
    private Logger logger;

    @Test
    void testShouldSetValueWhenParamCanBeConverted() {
        // GIVEN
        String param = GeneratorType.DEFAULT.getValue();

        // WHEN
        underTest.setAsText(param);

        // THEN
        assertEquals(GeneratorType.DEFAULT, underTest.getValue());
    }

    @Test
    void testShouldThrowExceptionWhenParamCannotBeConverted() {
        // GIVEN
        String param = "bad param";
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        Mockito.when(messageResolver.withUSLocale(MSG_REQUEST_PARAM_CANNOT_BE_CONVERTED, param)).thenReturn(RESOLVED_MESSAGE);

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.setAsText(param));
        verify(messageResolver).withUSLocale(MSG_REQUEST_PARAM_CANNOT_BE_CONVERTED, param);
        verify(logger).error(RESOLVED_MESSAGE);
        verify(messageResolver).withRequestLocale(MSG_REQUEST_PARAM_CANNOT_BE_CONVERTED, param);
    }
}