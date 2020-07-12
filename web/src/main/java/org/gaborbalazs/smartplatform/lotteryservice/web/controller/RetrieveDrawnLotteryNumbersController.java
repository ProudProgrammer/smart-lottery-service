package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.Draw;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.retrieve.iface.RetrieveDrawnNumbersService;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.RetrieveDrawnLotteryNumbersApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.RetrieveDrawnLotteryNumbersSwaggerApi;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class RetrieveDrawnLotteryNumbersController extends BaseController implements RetrieveDrawnLotteryNumbersApi, RetrieveDrawnLotteryNumbersSwaggerApi {

    private final RetrieveDrawnNumbersService retrieveDrawnNumbersService;

    RetrieveDrawnLotteryNumbersController(RetrieveDrawnNumbersService retrieveDrawnNumbersService) {
        this.retrieveDrawnNumbersService = retrieveDrawnNumbersService;
    }

    @Override
    public List<? extends Draw> retrieve(LotteryType lotteryType) {
        List<? extends Draw> result = Collections.emptyList();
        if (lotteryType == LotteryType.FIVE_OUT_OF_NINETY) {
            result = retrieveDrawnNumbersService.retrieveAllFiveOutOfNinetyDraws();
        } else if (lotteryType == LotteryType.SIX_OUT_OF_FORTY_FIVE) {
            result = retrieveDrawnNumbersService.retrieveAllSixOutOfFortyFiveDraws();
        } else if (lotteryType == LotteryType.SCANDINAVIAN) {
            result = retrieveDrawnNumbersService.retrieveAllScandinavianDraws();
        }
        return result;
    }
}
