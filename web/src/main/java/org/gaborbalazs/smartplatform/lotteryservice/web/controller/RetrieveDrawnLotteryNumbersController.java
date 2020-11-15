package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.Draw;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.retrieve.iface.RetrieveDrawnNumbersService;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.RetrieveDrawnLotteryNumbersApi;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class RetrieveDrawnLotteryNumbersController implements RetrieveDrawnLotteryNumbersApi {

    private final RetrieveDrawnNumbersService retrieveDrawnNumbersService;

    RetrieveDrawnLotteryNumbersController(RetrieveDrawnNumbersService retrieveDrawnNumbersService) {
        this.retrieveDrawnNumbersService = retrieveDrawnNumbersService;
    }

    @Override
    public List<Draw> retrieve(LotteryType lotteryType) {
        return retrieveDrawnNumbersService.retrieveAllByLotteryType(lotteryType);
    }
}
