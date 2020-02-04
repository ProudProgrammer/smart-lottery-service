package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.ExperimentalFiveOutOfNinetyNumberGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExperimentalLotteryNumberGeneratorTest {

    @InjectMocks
    private ExperimentalLotteryNumberGenerator underTest;

    @Mock
    private ExperimentalFiveOutOfNinetyNumberGenerator experimentalFiveOutOfNinetyNumberGenerator;

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityLargerOrEqualsThenPoolSize() {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        int quantity = 5;
        int poolSize = 5;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generate(quantity, poolSize));
    }

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityIsNot5AndPoolSizeIsNot90() {
        // GIVEN
        Class<UnsupportedOperationException> expectedExceptionClass = UnsupportedOperationException.class;
        int quantity = 6;
        int poolSize = 45;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generate(quantity, poolSize));
    }

    @Test
    void testGenerateShouldReturnSetOf5Element() {
        // GIVEN
        int quantity = 5;
        int poolSize = 90;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        when(experimentalFiveOutOfNinetyNumberGenerator.generate()).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(quantity, poolSize);

        // THEN
        assertEquals(expectedResult, result);
    }
}
