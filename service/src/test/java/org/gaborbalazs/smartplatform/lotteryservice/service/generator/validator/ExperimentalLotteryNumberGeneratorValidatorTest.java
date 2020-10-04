package org.gaborbalazs.smartplatform.lotteryservice.service.generator.validator;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class ExperimentalLotteryNumberGeneratorValidatorTest {

    @InjectMocks
    private ExperimentalLotteryNumberGeneratorValidator underTest;

    @Mock
    private MessageSource messageSource;

    @Mock
    private RequestContext requestContext;

    @Mock
    private Logger logger;

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityEqualsPoolSize() {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        int quantity = 5;
        int poolSize = 5;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.validate(quantity, poolSize));
    }

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityLargerThenPoolSize() {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        int quantity = 6;
        int poolSize = 5;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.validate(quantity, poolSize));
    }

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityIsNot5AndPoolSizeIsNot90() {
        // GIVEN
        Class<UnsupportedOperationException> expectedExceptionClass = UnsupportedOperationException.class;
        int quantity = 6;
        int poolSize = 45;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.validate(quantity, poolSize));
    }

    @Test
    void testGenerateShouldThrowExceptionWhenPoolSizeIsNot90() {
        // GIVEN
        Class<UnsupportedOperationException> expectedExceptionClass = UnsupportedOperationException.class;
        int quantity = 5;
        int poolSize = 45;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.validate(quantity, poolSize));
    }

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityIsNot5() {
        // GIVEN
        Class<UnsupportedOperationException> expectedExceptionClass = UnsupportedOperationException.class;
        int quantity = 6;
        int poolSize = 90;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.validate(quantity, poolSize));
    }
}