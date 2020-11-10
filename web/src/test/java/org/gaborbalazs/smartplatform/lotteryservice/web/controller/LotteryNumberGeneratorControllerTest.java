package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.LotteryTypeGeneratorTypeRequest;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.QuantityPoolSizeGeneratorTypeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class LotteryNumberGeneratorControllerTest {

    @InjectMocks
    private LotteryNumberGeneratorController underTest;

    @Mock
    private LotteryNumberGenerator lotteryNumberGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateWithLotteryTypeAndDefaultGeneratorType() {
        // GIVEN
        LotteryType lotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        LotteryTypeGeneratorTypeRequest request = new LotteryTypeGeneratorTypeRequest(lotteryType, generatorType);
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        GeneratedNumbers expectedResult = GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(lotteryType.name())
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
        when(lotteryNumberGenerator.generate(lotteryType, generatorType)).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(request);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateWithQuantityAndPoolSizeAndExperimentalGeneratorType() {
        // GIVEN
        int quantity = 6;
        int poolSize = 59;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        QuantityPoolSizeGeneratorTypeRequest request = new QuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);
        List<Integer> drawnNumbers = List.of(1, 2, 3, 4, 5);
        GeneratedNumbers expectedResult = GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(quantity + "/" + poolSize)
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
        when(lotteryNumberGenerator.generate(quantity, poolSize, generatorType)).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(request);

        // THEN
        assertEquals(expectedResult, result);
    }
}
