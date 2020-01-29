package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NumberGeneratorTest {

    private NumberGenerator underTest;

    @BeforeEach
    void setUp() {
        underTest = new NumberGenerator(ThreadLocalRandom.current());
    }

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityLargerThenPoolSize() {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        int quantity = 10;
        int poolSize = 9;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generate(quantity, poolSize));
    }

    @Test
    void testGenerateShouldReturn5ElementWhenQuantity5AndPoolSize90() {
        // GIVEN
        int expectedResultSize = 5;
        int lowerLimit = 0;
        int upperLimit = 90;

        // WHEN
        var result = underTest.generate(5, 90);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> Assertions.assertTrue(number > lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateShouldReturn6ElementWhenQuantity6AndPoolSize45() {
        // GIVEN
        int expectedResultSize = 6;
        int lowerLimit = 0;
        int upperLimit = 45;

        // WHEN
        var result = underTest.generate(6, 45);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> Assertions.assertTrue(number > lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateShouldReturn7ElementWhenQuantity7AndPoolSize35() {
        // GIVEN
        int expectedResultSize = 7;
        int lowerLimit = 0;
        int upperLimit = 35;

        // WHEN
        var result = underTest.generate(7, 35);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> Assertions.assertTrue(number > lowerLimit && number <= upperLimit));
    }
}