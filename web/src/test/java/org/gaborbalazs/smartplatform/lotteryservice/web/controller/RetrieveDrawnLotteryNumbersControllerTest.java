package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.Draw;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.retrieve.iface.RetrieveDrawnNumbersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveDrawnLotteryNumbersControllerTest {

    @InjectMocks
    private RetrieveDrawnLotteryNumbersController underTest;

    @Mock
    private RetrieveDrawnNumbersService retrieveDrawnNumbersService;

    @Test
    void testRetrieve() {
        // GIVEN
        LotteryType lotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        List<Draw> expectedDraws = createDraws();
        when(retrieveDrawnNumbersService.retrieveAllByLotteryType(lotteryType)).thenReturn(expectedDraws);

        // WHEN
        var result = underTest.retrieve(lotteryType);

        // THEN
        assertEquals(expectedDraws, result);
    }

    private List<Draw> createDraws() {
        Draw draw = Draw.newDraw().build();
        return Collections.singletonList(draw);
    }
}