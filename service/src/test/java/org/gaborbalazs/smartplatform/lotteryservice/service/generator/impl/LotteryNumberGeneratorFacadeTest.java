package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LotteryNumberGeneratorFacadeTest {

    @InjectMocks
    private LotteryNumberGeneratorFacade underTest;

    @Mock
    private LotteryNumberGeneratorStrategyProvider lotteryNumberGeneratorStrategyProvider;

    @Test
    void testGenerateWithLotteryTypeShouldUseDefaultLotteryNumberGeneratorStrategyWhenGeneratorTypeDefault() {
        // GIVEN
        LotteryNumberGeneratorStrategy lotteryNumberGeneratorStrategy = mock(LotteryNumberGeneratorStrategy.class);
        LotteryType lotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        GeneratedNumbers expectedResult = GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(lotteryType.name())
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
        when(lotteryNumberGeneratorStrategyProvider.get(generatorType)).thenReturn(lotteryNumberGeneratorStrategy);
        when(lotteryNumberGeneratorStrategy.generateWithoutReplacement(lotteryType.getQuantity(), lotteryType.getPool())).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generate(lotteryType, generatorType);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateWithLotteryTypeShouldUseExperimentalLotteryNumberGeneratorStrategyWhenGeneratorTypeExperimental() {
        // GIVEN
        LotteryNumberGeneratorStrategy lotteryNumberGeneratorStrategy = mock(LotteryNumberGeneratorStrategy.class);
        LotteryType lotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        GeneratedNumbers expectedResult = GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(lotteryType.name())
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
        when(lotteryNumberGeneratorStrategyProvider.get(generatorType)).thenReturn(lotteryNumberGeneratorStrategy);
        when(lotteryNumberGeneratorStrategy.generateWithoutReplacement(lotteryType.getQuantity(), lotteryType.getPool())).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generate(lotteryType, generatorType);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldUseDefaultLotteryNumberGeneratorStrategyWhenGeneratorTypeDefault() {
        // GIVEN
        LotteryNumberGeneratorStrategy lotteryNumberGeneratorStrategy = mock(LotteryNumberGeneratorStrategy.class);
        int quantity = 6;
        int poolSize = 60;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        GeneratedNumbers expectedResult = GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(quantity + "/" + poolSize)
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
        when(lotteryNumberGeneratorStrategyProvider.get(generatorType)).thenReturn(lotteryNumberGeneratorStrategy);
        when(lotteryNumberGeneratorStrategy.generateWithoutReplacement(quantity, poolSize)).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generate(quantity, poolSize, generatorType);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldUseExperimentalLotteryNumberGeneratorStrategyWhenGeneratorTypeExperimental() {
        // GIVEN
        LotteryNumberGeneratorStrategy lotteryNumberGeneratorStrategy = mock(LotteryNumberGeneratorStrategy.class);
        int quantity = 6;
        int poolSize = 60;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        GeneratedNumbers expectedResult = GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(quantity + "/" + poolSize)
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
        when(lotteryNumberGeneratorStrategyProvider.get(generatorType)).thenReturn(lotteryNumberGeneratorStrategy);
        when(lotteryNumberGeneratorStrategy.generateWithoutReplacement(quantity, poolSize)).thenReturn(drawnNumbers);

        // WHEN
        var result = underTest.generate(quantity, poolSize, generatorType);

        // THEN
        assertEquals(expectedResult, result);
    }
}