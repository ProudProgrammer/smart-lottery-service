package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.ExperimentalFiveOutOfNinetyNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.validator.ExperimentalLotteryNumberGeneratorValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExperimentalLotteryNumberGeneratorTest {

    @InjectMocks
    private ExperimentalLotteryNumberGenerator underTest;

    @Mock
    private ExperimentalFiveOutOfNinetyNumberGenerator experimentalFiveOutOfNinetyNumberGenerator;

    @Mock
    private ExperimentalLotteryNumberGeneratorValidator experimentalLotteryNumberGeneratorValidator;

    @Test
    void testShouldReturnSetOf5ElementWhenGenerateWithoutReplacementCalled() {
        // GIVEN
        int quantity = 5;
        int poolSize = 90;
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        when(experimentalFiveOutOfNinetyNumberGenerator.generate()).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generateWithoutReplacement(quantity, poolSize);

        // THEN
        assertEquals(drawnNumbers, result);
    }

    @Test
    void testShouldThrowUnsupportedOperationExceptionWhenGenerateWithReplacementCalled() {
        // GIVEN
        Class<UnsupportedOperationException> expectedExceptionClass = UnsupportedOperationException.class;
        int quantity = 6;
        int poolSize = 45;
        when(experimentalLotteryNumberGeneratorValidator.unsupportedOperation()).thenReturn(new UnsupportedOperationException());

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generateWitHReplacement(quantity, poolSize));
    }
}
