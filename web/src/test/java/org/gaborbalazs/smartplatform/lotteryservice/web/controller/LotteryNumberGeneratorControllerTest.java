package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.web.facade.LotteryNumberGeneratorFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LotteryNumberGeneratorControllerTest {

    private static final LotteryType TEST_LOTTERY_TYPE = LotteryType.FIVE_OUT_OF_NINETY;
    private static final GeneratorType TEST_GENERATOR_TYPE = GeneratorType.DEFAULT;
    private static final SortedSet<Integer> EXPECTED_RESULT = new TreeSet<>(List.of(1, 2, 3, 4, 5));

    @InjectMocks
    private LotteryNumberGeneratorController underTest;

    @Mock
    private LotteryNumberGeneratorFacade lotteryNumberGeneratorFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void generate() {
        // GIVEN
        when(lotteryNumberGeneratorFacade.generate(TEST_LOTTERY_TYPE, TEST_GENERATOR_TYPE)).thenReturn(EXPECTED_RESULT);

        // WHEN
        var result = underTest.generate(TEST_LOTTERY_TYPE, TEST_GENERATOR_TYPE);

        // THEN
        assertEquals(EXPECTED_RESULT, result);
    }
}