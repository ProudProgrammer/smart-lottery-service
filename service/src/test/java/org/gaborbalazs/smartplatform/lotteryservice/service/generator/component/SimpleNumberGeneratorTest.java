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
    @CsvSource({"0,90", "5,0", "5,1001", "5,5", "6,5", "-1,90", "5,-1"})
    void testGenerateUniqueNumbersFromSamePoolShouldThrowException(int quantity, int poolSize) {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        when(messageFactory.create(anyString(), any(Object.class))).thenReturn("exception");

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generateUniqueNumbersFromSamePool(quantity, poolSize));
    }

    @Test
    void testGenerateUniqueNumbersFromSamePoolShouldReturn5ElementsWhenQuantity5AndPoolSize90() {
        // GIVEN
        int expectedResultSize = 5;
        int lowerLimit = 1;
        int upperLimit = 90;

        // WHEN
        var result = underTest.generateUniqueNumbersFromSamePool(expectedResultSize, upperLimit);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> assertTrue(number >= lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateUniqueNumbersFromSamePoolShouldReturn6ElementsWhenQuantity6AndPoolSize45() {
        // GIVEN
        int expectedResultSize = 6;
        int lowerLimit = 1;
        int upperLimit = 45;

        // WHEN
        var result = underTest.generateUniqueNumbersFromSamePool(expectedResultSize, upperLimit);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> assertTrue(number >= lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateUniqueNumbersFromSamePoolShouldReturn7ElementsWhenQuantity7AndPoolSize35() {
        // GIVEN
        int expectedResultSize = 7;
        int lowerLimit = 1;
        int upperLimit = 35;

        // WHEN
        var result = underTest.generateUniqueNumbersFromSamePool(expectedResultSize, upperLimit);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> assertTrue(number >= lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateUniqueNumbersFromSamePoolShouldReturn10ElementsWhenQuantity10AndPoolSize100() {
        // GIVEN
        int expectedResultSize = 10;
        int lowerLimit = 1;
        int upperLimit = 100;

        // WHEN
        var result = underTest.generateUniqueNumbersFromSamePool(expectedResultSize, upperLimit);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> assertTrue(number >= lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateUniqueNumbersFromSamePoolShouldReturn9ElementsWhenQuantity9AndLowerLimit1001AndUpperLimit1010() {
        // GIVEN
        int expectedResultSize = 9;
        int lowerLimit = 1001;
        int upperLimit = 1010;

        // WHEN
        var result = underTest.generateUniqueNumbersFromSamePool(expectedResultSize, lowerLimit, upperLimit);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> assertTrue(number >= lowerLimit && number <= upperLimit));
    }

    @ParameterizedTest
    @CsvSource({"-1,9", "6,-1", "0, 9", "6, 0"})
    void testGenerateWithRecurrenceShouldThrowException(int quantity, int upperLimit) {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        when(messageFactory.create(anyString(), any(Object.class))).thenReturn("exception");

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generateWithRecurrence(quantity, upperLimit));
    }

    @Test
    void testGenerateWithRecurrenceShouldReturn6ElementsWhenQuantity6AndUpperLimit9() {
        // GIVEN
        int expectedResultSize = 6;
        int upperLimit = 9;

        // WHEN
        var result = underTest.generateWithRecurrence(expectedResultSize, upperLimit);

        // THEN
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> assertTrue(number < upperLimit));
    }
}
