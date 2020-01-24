package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @Test
    void testGenerateShouldReturn6ElementWhenQuantity6() {
        // GIVEN
        int expectedResultSize = 6;
        int lowerLimit = 0;
        int upperLimit = 45;

        // WHEN
        var result = underTest.generate(6, 45);

        // THEN
        Assertions.assertEquals(result.size(), expectedResultSize);
        result.forEach(number -> Assertions.assertTrue(number > lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateShouldReturn7ElementWhenQuantity7() {
        // GIVEN
        int expectedResultSize = 7;
        int lowerLimit = 0;
        int upperLimit = 35;

        // WHEN
        var result = underTest.generate(7, 35);

        // THEN
        Assertions.assertEquals(result.size(), expectedResultSize);
        result.forEach(number -> Assertions.assertTrue(number > lowerLimit && number <= upperLimit));
    }
}