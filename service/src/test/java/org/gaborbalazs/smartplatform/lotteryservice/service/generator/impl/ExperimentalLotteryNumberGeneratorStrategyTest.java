package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ExperimentalLotteryNumberGeneratorStrategyTest {

    private ExperimentalLotteryNumberGeneratorStrategy underTest;

    private static Stream<Arguments> provideUsedFifthsAndSortedSetAsDrawnNumbers() {
        return Stream.of(
                Arguments.of(1, new TreeSet<>(List.of(1, 2, 3, 4, 5))),
                Arguments.of(1, new TreeSet<>(List.of(19, 20, 27, 35, 36))),
                Arguments.of(2, new TreeSet<>(List.of(1, 18, 19, 20, 36))),
                Arguments.of(3, new TreeSet<>(List.of(19, 37, 38, 54, 73))),
                Arguments.of(4, new TreeSet<>(List.of(18, 37, 38, 72, 73))),
                Arguments.of(5, new TreeSet<>(List.of(1, 19, 37, 55, 73))),
                Arguments.of(5, new TreeSet<>(List.of(18, 36, 54, 72, 90)))
        );
    }

    @BeforeEach
    void setUp() {
        underTest = new ExperimentalLotteryNumberGeneratorStrategy(ThreadLocalRandom.current());
    }

    /**
     * Test method to check unit test works well.
     * @param sortedSet is the drawn numbers
     * @param expectedUsedFifths is the expected value
     */
    @ParameterizedTest
    @MethodSource("provideUsedFifthsAndSortedSetAsDrawnNumbers")
    void testCountUsedFifths(int expectedUsedFifths, SortedSet<Integer> sortedSet) {
        // GIVEN
        // WHEN
        var result = countUsedFifths(sortedSet);

        // THEN
        assertEquals(expectedUsedFifths, result);
    }

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityLargerThenPoolSize() {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generate(10, 9));
    }

    @Test
    void testGenerateShouldThrowExceptionWhenQuantityIsNot5AndPoolSizeIsNot90() {
        // GIVEN
        Class<UnsupportedOperationException> expectedExceptionClass = UnsupportedOperationException.class;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generate(6, 45));
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
        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> Assertions.assertTrue(number > lowerLimit && number <= upperLimit));
    }

    @Test
    void testGenerateShouldReturn5ElementInWhich2Or3EvenIn3Or4FifthWhenQuantity5() {
        // GIVEN
        int expectedResultSize = 5;
        int lowerLimit = 0;
        int upperLimit = 90;
        int[] expectedEvenNumbers = {2, 3};
        int[] expectedUsedFifths = {3, 4};

        // WHEN
        var result = underTest.generate(5, 90);

        // THEN
        int evenNumbers = countEvenNumbers(result);
        int usedFifths = countUsedFifths(result);

        assertEquals(expectedResultSize, result.size());
        result.forEach(number -> Assertions.assertTrue(number > lowerLimit && number <= upperLimit));
        assertTrue(Arrays.stream(expectedEvenNumbers).anyMatch(expectedEvenNumber -> expectedEvenNumber == evenNumbers));
        assertTrue(Arrays.stream(expectedUsedFifths).anyMatch(expectedUsedFifth -> expectedUsedFifth == usedFifths));
    }

    private int countEvenNumbers(SortedSet<Integer> sortedSet) {
        return (int) sortedSet.stream().filter(number -> number % 2 == 0).count();
    }

    private int countUsedFifths(SortedSet<Integer> sortedSet) {
        int[] usedFifths = {0, 0, 0, 0, 0};
        for (Integer drawnNumber : sortedSet) {
            int fifth = ((int) Math.ceil(((double) drawnNumber / 18))) - 1;
            usedFifths[fifth] = 1;
        }
        return (int) Arrays.stream(usedFifths).filter(usedFifth -> usedFifth == 1).count();
    }
}