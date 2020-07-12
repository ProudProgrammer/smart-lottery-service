package org.gaborbalazs.smartplatform.lotteryservice.web.api;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.Draw;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/retrieve")
public interface RetrieveDrawnLotteryNumbersApi {

    @RequestMapping(value = "/{lotteryType}/drawnNumbers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<? extends Draw> retrieve(@PathVariable("lotteryType") LotteryType lotteryType);
}
