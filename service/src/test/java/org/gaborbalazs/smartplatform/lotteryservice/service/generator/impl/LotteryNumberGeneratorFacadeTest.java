package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.validator.LotteryNumberGeneratorFacadeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    private LotteryNumberGeneratorFacadeValidator lotteryNumberGeneratorFacadeValidator;

    @BeforeEach
    void setUp() {
        defaultLotteryNumberGeneratorStrategy = mock(DefaultLotteryNumberGenerator.class);
        experimentalLotteryNumberGeneratorStrategy = mock(ExperimentalLotteryNumberGenerator.class);
        lotteryNumberGeneratorFacadeValidator = mock(LotteryNumberGeneratorFacadeValidator.class);
        underTest = new LotteryNumberGeneratorFacade(defaultLotteryNumberGeneratorStrategy, experimentalLotteryNumberGeneratorStrategy, lotteryNumberGeneratorFacadeValidator);
    }

    @Test
    void testGenerateWithLotteryTypeShouldUseDefaultLotteryNumberGeneratorStrategyWhenGeneratorTypeDefault() {
        // GIVEN
        LotteryType lotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        GeneratedNumbers expectedResult = GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(lotteryType.name())
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
        when(defaultLotteryNumberGeneratorStrategy.generateWithoutReplacement(lotteryType.getQuantity(), lotteryType.getPool())).thenReturn(drawnNumbers);

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
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        GeneratedNumbers expectedResult = GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(lotteryType.name())
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
        when(experimentalLotteryNumberGeneratorStrategy.generateWithoutReplacement(lotteryType.getQuantity(), lotteryType.getPool())).thenReturn(drawnNumbers);

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
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        GeneratedNumbers expectedResult = GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(quantity + "/" + poolSize)
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
        when(defaultLotteryNumberGeneratorStrategy.generateWithoutReplacement(quantity, poolSize)).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generate(quantity, poolSize, generatorType);

        // THEN
        verify(lotteryNumberGeneratorFacadeValidator).validate(quantity, poolSize);
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldUseExperimentalLotteryNumberGeneratorStrategyWhenGeneratorTypeExperimental() {
        // GIVEN
        int quantity = 6;
        int poolSize = 60;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        GeneratedNumbers expectedResult = GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(quantity + "/" + poolSize)
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
        when(experimentalLotteryNumberGeneratorStrategy.generateWithoutReplacement(quantity, poolSize)).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generate(quantity, poolSize, generatorType);

        // THEN
        verify(lotteryNumberGeneratorFacadeValidator).validate(quantity, poolSize);
        assertEquals(expectedResult, result);
    }
}