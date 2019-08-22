package org.gaborbalazs.smartplatform.lotteryservice.web.api;

import java.util.Set;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/lottery")
public interface LotteryNumberGeneratorApi {

    @RequestMapping(value = "/{lotteryType}/numbers/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Set<Integer> generateRandom(@PathVariable("lotteryType") LotteryType lotteryType);
}
