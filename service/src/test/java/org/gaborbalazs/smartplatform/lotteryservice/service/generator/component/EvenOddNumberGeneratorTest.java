package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EvenOddNumberGeneratorTest {

    @InjectMocks
    private EvenOddNumberGenerator underTest;

    @Mock
    private Random random;

    @Mock
    private ListShuffler listShuffler;

    @Mock
    private MessageResolver messageResolver;

    @Mock
    private Logger logger;

    @Test
    void testGenerateEvenNumberShouldThrowExceptionWhenUpperLimitLessOrEqualThanLowerLimit() {
        // GIVEN
        Exception exception = null;
        int lowerLimit = 19;
        int upperLimit = 19;
        Set<Integer> exceptions = Collections.emptySet();
        String msg = "exception";
        when(messageResolver.withUSLocale(anyString(), any(Object.class))).thenReturn(msg);
        when(messageResolver.withRequestLocale(anyString(), any(Object.class))).thenReturn(msg);

        // WHEN
        try {
            underTest.generateEvenNumber(lowerLimit, upperLimit, exceptions);
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        // THEN
        verify(logger).error(msg);
        assertNotNull(exception);
        assertEquals(msg, exception.getMessage());
    }

    @Test
    void testGenerateOddNumberShouldThrowExceptionWhenUpperLimitLessOrEqualThanLowerLimit() {
        // GIVEN
        Exception exception = null;
        int lowerLimit = 20;
        int upperLimit = 19;
        Set<Integer> exceptions = Collections.emptySet();
        String msg = "exception";
        when(messageResolver.withUSLocale(anyString(), any(Object.class))).thenReturn(msg);
        when(messageResolver.withRequestLocale(anyString(), any(Object.class))).thenReturn(msg);

        // WHEN
        try {
            underTest.generateEvenNumber(lowerLimit, upperLimit, exceptions);
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        // THEN
        verify(logger).error(msg);
        assertNotNull(exception);
        assertEquals(msg, exception.getMessage());
    }

    @Test
    void testGenerateEvenNumberShouldReturnEvenNumberBetweenLowerLimitAndUpperLimitWhenNoExceptions() {
        // GIVEN
        int lowerLimit = 19;
        int upperLimit = 36;
        Set<Integer> exceptions = Collections.emptySet();
        int expectedResult = 36;
        List<Integer> evens = List.of(20, 22, 24, 26, 28, 30, 32, 34, 36);
        when(listShuffler.shuffle(evens)).thenReturn(evens);
        when(random.nextInt(9)).thenReturn(8);

        // WHEN
        var result = underTest.generateEvenNumber(lowerLimit, upperLimit, exceptions);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateEvenNumberShouldReturnEvenNumberBetweenLowerLimitAndUpperLimitWhenExceptions() {
        // GIVEN
        int lowerLimit = 19;
        int upperLimit = 36;
        Set<Integer> exceptions = Set.of(19, 20, 21, 22, 23);
        int expectedResult = 30;
        List<Integer> evensWithoutExceptions = List.of(24, 26, 28, 30, 32, 34, 36);
        when(listShuffler.shuffle(evensWithoutExceptions)).thenReturn(evensWithoutExceptions);
        when(random.nextInt(7)).thenReturn(3);

        // WHEN
        var result = underTest.generateEvenNumber(lowerLimit, upperLimit, exceptions);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateOddNumberShouldReturnOddNumberBetweenLowerLimitAndUpperLimitWhenNoExceptions() {
        // GIVEN
        int lowerLimit = 19;
        int upperLimit = 36;
        Set<Integer> exceptions = Collections.emptySet();
        int expectedResult = 35;
        List<Integer> odds = List.of(19, 21, 23, 25, 27, 29, 31, 33, 35);
        when(listShuffler.shuffle(odds)).thenReturn(odds);
        when(random.nextInt(9)).thenReturn(8);

        // WHEN
        var result = underTest.generateOddNumber(lowerLimit, upperLimit, exceptions);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateOddNumberShouldReturnOddNumberBetweenLowerLimitAndUpperLimitWhenExceptions() {
        // GIVEN
        int lowerLimit = 19;
        int upperLimit = 36;
        Set<Integer> exceptions = Set.of(19, 20, 21, 22, 23);
        int expectedResult = 35;
        List<Integer> oddsWithoutExceptions = List.of(25, 27, 29, 31, 33, 35);
        when(listShuffler.shuffle(oddsWithoutExceptions)).thenReturn(oddsWithoutExceptions);
        when(random.nextInt(6)).thenReturn(5);

        // WHEN
        var result = underTest.generateOddNumber(lowerLimit, upperLimit, exceptions);

        // THEN
        assertEquals(expectedResult, result);
    }
}
