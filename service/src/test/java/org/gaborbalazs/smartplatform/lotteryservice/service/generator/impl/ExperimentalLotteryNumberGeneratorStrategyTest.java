package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

class ExperimentalLotteryNumberGeneratorStrategyTest {

    @InjectMocks
    private ExperimentalLotteryNumberGeneratorStrategy underTest;

    @Mock
    private Random random;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityLargerThenPoolSize() {
        // GIVEN
        Class expectedExceptionClass = IllegalArgumentException.class;

        // WHEN
        // THEN
        Assertions.assertThrows(expectedExceptionClass, () -> {
            underTest.generate(10, 9);
        });
    }

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityIsNot5AndPoolSizeIsNot90() {
        // GIVEN
        Class expectedExceptionClass = UnsupportedOperationException.class;

        // WHEN
        // THEN
        Assertions.assertThrows(expectedExceptionClass, () -> {
            underTest.generate(6, 45);
        });
    }

    @Test
    void testGenerateShouldReturn5ElementWhenQuantity5() {
        // GIVEN
        int expectedResultSize = 5;
        int lowerLimit = 0;
        int upperLimit = 90;

        // WHEN
        var result = underTest.generate(5, 90);

        // THEN
        Assertions.assertEquals(result.size(), expectedResultSize);
        result.forEach(number -> Assertions.assertTrue(number > lowerLimit && number <= upperLimit));
    }
}