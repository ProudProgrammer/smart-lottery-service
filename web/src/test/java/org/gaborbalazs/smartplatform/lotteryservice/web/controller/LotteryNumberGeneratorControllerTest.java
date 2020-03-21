package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.DrawnNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

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
        SortedSet<Integer> drawnNumbers = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        DrawnNumbers expectedResult = new DrawnNumbers(generatorType, drawnNumbers);
        when(lotteryNumberGenerator.generate(lotteryType, generatorType)).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(lotteryType, generatorType);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateWithQuantityAndPoolSizeAndExperimentalGeneratorType() {
        // GIVEN
        int quantity = 6;
        int poolSize = 59;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        SortedSet<Integer> drawnNumbers = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        DrawnNumbers expectedResult = new DrawnNumbers(generatorType, drawnNumbers);
        when(lotteryNumberGenerator.generate(quantity, poolSize, generatorType)).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(quantity, poolSize, generatorType);

        // THEN
        assertEquals(expectedResult, result);
    }
}
