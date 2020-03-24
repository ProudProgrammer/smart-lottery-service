package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.ExperimentalFiveOutOfNinetyNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.MessageFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExperimentalLotteryNumberGeneratorTest {

    @InjectMocks
    private ExperimentalLotteryNumberGenerator underTest;

    @Mock
    private ExperimentalFiveOutOfNinetyNumberGenerator experimentalFiveOutOfNinetyNumberGenerator;

    @Mock
    private MessageFactory messageFactory;

    @Mock
    private Logger logger;

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityLargerOrEqualsThenPoolSize() {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        int quantity = 5;
        int poolSize = 5;
        when(messageFactory.create(anyString(), any(Object.class))).thenReturn("exception");

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
        when(messageFactory.create(anyString(), any(Object.class))).thenReturn("exception");

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generate(quantity, poolSize));
    }

    @Test
    void testGenerateShouldReturnSetOf5Element() {
        // GIVEN
        int quantity = 5;
        int poolSize = 90;
        SortedSet<Integer> drawnNumbers = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        when(experimentalFiveOutOfNinetyNumberGenerator.generate()).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generate(quantity, poolSize);

        // THEN
        assertEquals(drawnNumbers, result);
    }
}
