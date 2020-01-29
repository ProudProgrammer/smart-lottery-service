package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void generate() {
        // GIVEN
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        LotteryType testLotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType testGeneratorType = GeneratorType.DEFAULT;
        when(lotteryNumberGenerator.generate(testLotteryType, testGeneratorType)).thenReturn(expectedResult);

        // WHEN
        var result = underTest.generate(testLotteryType, testGeneratorType);

        // THEN
        assertEquals(expectedResult, result);
    }

}