package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DefaultLotteryNumberGeneratorStrategyTest {

    @InjectMocks
    private DefaultLotteryNumberGeneratorStrategy underTest;

    @Mock
    private Random random;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityLargerThenPoolSize() {
        // GIVEN
        // WHEN
        // THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            underTest.generate(10, 9);
        });
    }

    @Test
    void testGenerateShouldReturn5ElementWhenQuantity5() {
        // GIVEN
        // WHEN
        var result = underTest.generate(5, 90);

        // THEN
        Assertions.assertEquals(result.size(), 5);
        result.forEach(number -> Assertions.assertTrue(number > 0 && number <= 90));
    }

    @Test
    void testGenerateShouldReturn6ElementWhenQuantity6() {
        // GIVEN
        // WHEN
        var result = underTest.generate(6, 45);

        // THEN
        Assertions.assertEquals(result.size(), 6);
        result.forEach(number -> Assertions.assertTrue(number > 0 && number <= 45));
    }

    @Test
    void testGenerateShouldReturn7ElementWhenQuantity7() {
        // GIVEN
        // WHEN
        var result = underTest.generate(7, 35);

        // THEN
        Assertions.assertEquals(result.size(), 7);
        result.forEach(number -> Assertions.assertTrue(number > 0 && number <= 35));
    }
}