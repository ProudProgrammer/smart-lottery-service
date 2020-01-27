package org.gaborbalazs.smartplatform.lotteryservice.web.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl.LotteryNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LotteryNumberGeneratorFacadeTest {

    private static final LotteryType TEST_LOTTERY_TYPE = LotteryType.FIVE_OUT_OF_NINETY;
    private static final SortedSet<Integer> EXPECTED_RESULT_FOR_DEFAULT_GENERATOR_TYPE = new TreeSet<>(List.of(1, 2, 3, 4, 5));
    private static final SortedSet<Integer> EXPECTED_RESULT_FOR_EXPERIMENTAL_GENERATOR_TYPE = new TreeSet<>(List.of(10, 11, 12, 13, 14));

    private LotteryNumberGeneratorFacade underTest;
    private LotteryNumberGenerator defaultLotteryNumberGenerator;
    private LotteryNumberGenerator experimentalLotteryNumberGenerator;

    @BeforeEach
    void setUp() {
        defaultLotteryNumberGenerator = mock(LotteryNumberGenerator.class);
        experimentalLotteryNumberGenerator = mock(LotteryNumberGenerator.class);
        underTest = new LotteryNumberGeneratorFacade(defaultLotteryNumberGenerator, experimentalLotteryNumberGenerator);
        when(defaultLotteryNumberGenerator.generate(TEST_LOTTERY_TYPE)).thenReturn(EXPECTED_RESULT_FOR_DEFAULT_GENERATOR_TYPE);
        when(experimentalLotteryNumberGenerator.generate(TEST_LOTTERY_TYPE)).thenReturn(EXPECTED_RESULT_FOR_EXPERIMENTAL_GENERATOR_TYPE);
    }

    @Test
    void generateWhenGeneratorTypeIsDefault() {
        // GIVEN
        GeneratorType generatorType = GeneratorType.DEFAULT;

        // WHEN
        var result = underTest.generate(TEST_LOTTERY_TYPE, generatorType);

        // THEN
        assertEquals(EXPECTED_RESULT_FOR_DEFAULT_GENERATOR_TYPE, result);
    }

    @Test
    void generateWhenGeneratorTypeIsExperimental() {
        // GIVEN
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;

        // WHEN
        var result = underTest.generate(TEST_LOTTERY_TYPE, generatorType);

        // THEN
        assertEquals(EXPECTED_RESULT_FOR_EXPERIMENTAL_GENERATOR_TYPE, result);
    }
}