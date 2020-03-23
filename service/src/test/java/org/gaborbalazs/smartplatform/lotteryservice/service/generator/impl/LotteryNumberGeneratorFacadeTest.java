package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.DrawnNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.MessageFactory;
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

/**
 * In this case {@link org.mockito.Mock} and {@link org.mockito.InjectMocks} annotations cannot be used
 * because {@link LotteryNumberGeneratorFacade} contains {@link DefaultLotteryNumberGenerator} and
 * {@link ExperimentalLotteryNumberGenerator} as {@link LotteryNumberGeneratorStrategy} interface and
 * Mockito injects them improperly.
 */
class LotteryNumberGeneratorFacadeTest {

    private LotteryNumberGeneratorFacade underTest;
    private LotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy;
    private LotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy;
    private MessageFactory messageFactory;

    @BeforeEach
    void setUp() {
        defaultLotteryNumberGeneratorStrategy = mock(DefaultLotteryNumberGenerator.class);
        experimentalLotteryNumberGeneratorStrategy = mock(ExperimentalLotteryNumberGenerator.class);
        messageFactory = mock(MessageFactory.class);
        underTest = new LotteryNumberGeneratorFacade(defaultLotteryNumberGeneratorStrategy, experimentalLotteryNumberGeneratorStrategy, messageFactory);
    }

    @ParameterizedTest
    @CsvSource({"0,90", "5,0", "5,1001", "5,5", "6,5"})
    void testGenerateShouldThrowException(int quantity, int poolSize) {
        // GIVEN
        GeneratorType generatorType = GeneratorType.DEFAULT;
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generate(quantity, poolSize, generatorType));
    }

    @Test
    void testGenerateWithLotteryTypeShouldUseDefaultLotteryNumberGeneratorStrategyWhenGeneratorTypeDefault() {
        // GIVEN
        LotteryType lotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        SortedSet<Integer> drawnNumbers = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        DrawnNumbers expectedResult = DrawnNumbers.newDrawnNumbers()
                .lotteryType(lotteryType.name())
                .generatorType(generatorType)
                .drawnNumbers(drawnNumbers)
                .build();
        when(defaultLotteryNumberGeneratorStrategy.generate(lotteryType.getQuantity(), lotteryType.getPool())).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generate(lotteryType, generatorType);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateWithLotteryTypeShouldUseExperimentalLotteryNumberGeneratorStrategyWhenGeneratorTypeExperimental() {
        // GIVEN
        LotteryType lotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        SortedSet<Integer> drawnNumbers = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        DrawnNumbers expectedResult = DrawnNumbers.newDrawnNumbers()
                .lotteryType(lotteryType.name())
                .generatorType(generatorType)
                .drawnNumbers(drawnNumbers)
                .build();
        when(experimentalLotteryNumberGeneratorStrategy.generate(lotteryType.getQuantity(), lotteryType.getPool())).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generate(lotteryType, generatorType);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldUseDefaultLotteryNumberGeneratorStrategyWhenGeneratorTypeDefault() {
        // GIVEN
        int quantity = 6;
        int poolSize = 60;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        SortedSet<Integer> drawnNumbers = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        DrawnNumbers expectedResult = DrawnNumbers.newDrawnNumbers()
                .lotteryType(quantity + "/" + poolSize)
                .generatorType(generatorType)
                .drawnNumbers(drawnNumbers)
                .build();
        when(defaultLotteryNumberGeneratorStrategy.generate(quantity, poolSize)).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generate(quantity, poolSize, generatorType);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldUseExperimentalLotteryNumberGeneratorStrategyWhenGeneratorTypeExperimental() {
        // GIVEN
        int quantity = 6;
        int poolSize = 60;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        SortedSet<Integer> drawnNumbers = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        DrawnNumbers expectedResult = DrawnNumbers.newDrawnNumbers()
                .lotteryType(quantity + "/" + poolSize)
                .generatorType(generatorType)
                .drawnNumbers(drawnNumbers)
                .build();
        when(experimentalLotteryNumberGeneratorStrategy.generate(quantity, poolSize)).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generate(quantity, poolSize, generatorType);

        // THEN
        assertEquals(expectedResult, result);
    }
}