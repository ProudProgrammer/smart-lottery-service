package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LotteryNumberGeneratorControllerTest {

    private static final String GENERATOR_TYPE = "Generator-Type";

    @InjectMocks
    private LotteryNumberGeneratorController underTest;

    @Mock
    private LotteryNumberGenerator lotteryNumberGenerator;

    @Mock
    private HttpServletResponse httpServletResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateWithLotteryTypeAndDefaultGeneratorType() {
        // GIVEN
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        LotteryType lotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        when(lotteryNumberGenerator.generate(lotteryType, generatorType)).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(lotteryType, generatorType);

        // THEN
        verify(httpServletResponse).addHeader(GENERATOR_TYPE, generatorType.getValue());
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateWithQuantityAndPoolSizeAndExperimentalGeneratorType() {
        // GIVEN
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(1, 2, 3, 4, 5, 6));
        int quantity = 6;
        int poolSize = 59;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        when(lotteryNumberGenerator.generate(quantity, poolSize, generatorType)).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(quantity, poolSize, generatorType);

        // THEN
        verify(httpServletResponse).addHeader(GENERATOR_TYPE, generatorType.getValue());
        assertEquals(expectedResult, result);
    }
}