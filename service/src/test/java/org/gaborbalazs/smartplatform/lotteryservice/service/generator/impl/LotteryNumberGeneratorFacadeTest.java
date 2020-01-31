package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LotteryNumberGeneratorFacadeTest {

    private LotteryNumberGeneratorFacade underTest;
    private LotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy;
    private LotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy;

    @BeforeEach
    void setUp() {
        defaultLotteryNumberGeneratorStrategy = mock(LotteryNumberGeneratorStrategy.class);
        experimentalLotteryNumberGeneratorStrategy = mock(LotteryNumberGeneratorStrategy.class);
        underTest = new LotteryNumberGeneratorFacade(defaultLotteryNumberGeneratorStrategy, experimentalLotteryNumberGeneratorStrategy);
    }

    @ParameterizedTest
    @CsvSource({"0,90", "5,0", "5,1001", "5,5", "6,5"})
    void testGenerateShouldThrowException(int quantity, int poolSize) {
        // GIVEN
        GeneratorType testGeneratorType = GeneratorType.DEFAULT;
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generate(quantity, poolSize, testGeneratorType));
    }

    @Test
    void testGenerateWithLotteryTypeShouldUseDefaultLotteryNumberGeneratorStrategyWhenGeneratorTypeDefault() {
        // GIVEN
        LotteryType testLotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType testGeneratorType = GeneratorType.DEFAULT;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        when(defaultLotteryNumberGeneratorStrategy.generate(testLotteryType.getQuantity(), testLotteryType.getPool())).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(testLotteryType, testGeneratorType);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateWithLotteryTypeShouldUseExperimentalLotteryNumberGeneratorStrategyWhenGeneratorTypeExperimental() {
        // GIVEN
        LotteryType testLotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType testGeneratorType = GeneratorType.EXPERIMENTAL;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        when(experimentalLotteryNumberGeneratorStrategy.generate(testLotteryType.getQuantity(), testLotteryType.getPool())).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(testLotteryType, testGeneratorType);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldUseDefaultLotteryNumberGeneratorStrategyWhenGeneratorTypeDefault() {
        // GIVEN
        int testQuantity = 6;
        int testPoolSize = 60;
        GeneratorType testGeneratorType = GeneratorType.DEFAULT;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(1, 2, 3, 4, 5, 6));
        when(defaultLotteryNumberGeneratorStrategy.generate(testQuantity, testPoolSize)).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(testQuantity, testPoolSize, testGeneratorType);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldUseExperimentalLotteryNumberGeneratorStrategyWhenGeneratorTypeExperimental() {
        // GIVEN
        int testQuantity = 6;
        int testPoolSize = 60;
        GeneratorType testGeneratorType = GeneratorType.EXPERIMENTAL;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(1, 2, 3, 4, 5, 6));
        when(experimentalLotteryNumberGeneratorStrategy.generate(testQuantity, testPoolSize)).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(testQuantity, testPoolSize, testGeneratorType);

        // THEN
        assertEquals(expectedResult, result);
    }
}