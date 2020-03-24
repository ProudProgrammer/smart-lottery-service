package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimpleNumberGeneratorTest {

    private SimpleNumberGenerator underTest;
    private MessageFactory messageFactory;
    private Logger logger;

    @BeforeEach
    void setUp() {
        messageFactory = mock(MessageFactory.class);
        logger = mock(Logger.class);
        underTest = new SimpleNumberGenerator(ThreadLocalRandom.current(), messageFactory, logger);
    }

    @ParameterizedTest
    @CsvSource({"0,90", "5,0", "5,1001", "5,5", "6,5"})
    void testGenerateShouldThrowException(int quantity, int poolSize) {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        when(messageFactory.create(anyString(), any(Object.class))).thenReturn("exception");

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generate(quantity, poolSize));
    }

    @Test
    void testGenerateShouldReturn5ElementsWhenQuantity5AndPoolSize90() {
        // GIVEN
        int expectedResultSize = 5;
        int lowerLimit = 1;
        int upperLimit = 90;

        // WHEN
        var result = underTest.generate(expectedResultSize, upperLimit);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> assertTrue(number >= lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateShouldReturn6ElementsWhenQuantity6AndPoolSize45() {
        // GIVEN
        int expectedResultSize = 6;
        int lowerLimit = 1;
        int upperLimit = 45;

        // WHEN
        var result = underTest.generate(expectedResultSize, upperLimit);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> assertTrue(number >= lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateShouldReturn7ElementsWhenQuantity7AndPoolSize35() {
        // GIVEN
        int expectedResultSize = 7;
        int lowerLimit = 1;
        int upperLimit = 35;

        // WHEN
        var result = underTest.generate(expectedResultSize, upperLimit);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> assertTrue(number >= lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateShouldReturn10ElementsWhenQuantity10AndPoolSize100() {
        // GIVEN
        int expectedResultSize = 10;
        int lowerLimit = 1;
        int upperLimit = 100;

        // WHEN
        var result = underTest.generate(expectedResultSize, upperLimit);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> assertTrue(number >= lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateShouldReturn9ElementsWhenQuantity9AndLowerLimit1001AndUpperLimit1010() {
        // GIVEN
        int expectedResultSize = 9;
        int lowerLimit = 1001;
        int upperLimit = 1010;

        // WHEN
        var result = underTest.generate(expectedResultSize, lowerLimit, upperLimit);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> assertTrue(number >= lowerLimit && number <= upperLimit));
    }
}
