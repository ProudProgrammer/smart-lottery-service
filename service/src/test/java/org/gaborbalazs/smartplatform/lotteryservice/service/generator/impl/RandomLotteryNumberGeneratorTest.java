package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class RandomLotteryNumberGeneratorTest {

    @InjectMocks
    private RandomLotteryNumberGenerator underTest;

    @Mock
    private Random random;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityLargerThenPoolSize() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            underTest.generate(10,9);
        });
    }

    @Test
    void testGenerateShouldReturn5ElementWhenQuantity5() {
        var result = underTest.generate(5, 90);
        Assertions.assertEquals(result.size(), 5);
        result.stream().forEach(number -> Assertions.assertTrue(number > 0 && number <= 90));
    }

    @Test
    void testGenerateShouldReturn6ElementWhenQuantity6() {
        var result = underTest.generate(6, 45);
        Assertions.assertEquals(result.size(), 6);
        result.stream().forEach(number -> Assertions.assertTrue(number > 0 && number <= 45));
    }

    @Test
    void testGenerateShouldReturn7ElementWhenQuantity7() {
        var result = underTest.generate(7, 35);
        Assertions.assertEquals(result.size(), 7);
        result.stream().forEach(number -> Assertions.assertTrue(number > 0 && number <= 35));
    }
}