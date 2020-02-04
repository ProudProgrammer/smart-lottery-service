package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.SimpleNumberGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

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
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        when(simpleNumberGenerator.generate(quantity, poolSize)).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(quantity, poolSize);

        // THEN
        assertEquals(expectedResult, result);
    }
}
