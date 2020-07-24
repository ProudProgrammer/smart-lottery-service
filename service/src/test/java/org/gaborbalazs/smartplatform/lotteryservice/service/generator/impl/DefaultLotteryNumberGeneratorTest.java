package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.SimpleNumberGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultLotteryNumberGeneratorTest {

    @InjectMocks
    private DefaultLotteryNumberGenerator underTest;

    @Mock
    private SimpleNumberGenerator simpleNumberGenerator;

    @Test
    void testGenerate() {
        // GIVEN
        int quantity = 5;
        int poolSize = 90;
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(quantity, poolSize)).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generateWithoutReplacement(quantity, poolSize);

        // THEN
        assertEquals(drawnNumbers, result);
    }
}
