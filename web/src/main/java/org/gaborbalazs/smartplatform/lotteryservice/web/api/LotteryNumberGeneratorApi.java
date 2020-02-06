package org.gaborbalazs.smartplatform.lotteryservice.web.api;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.SortedSet;

@RequestMapping("/lottery")
public interface LotteryNumberGeneratorApi {

    @RequestMapping(value = "/{lotteryType}/numbers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    SortedSet<Integer> generate(@PathVariable("lotteryType") LotteryType lotteryType, @RequestParam(defaultValue = "default") GeneratorType generatorType);

    @RequestMapping(value = "/numbers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    SortedSet<Integer> generate(@RequestParam int quantity, @RequestParam int poolSize, @RequestParam(defaultValue = "default") GeneratorType generatorType);
}
