package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import java.util.SortedSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorSwaggerApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.converter.LotteryTypeConverter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LotteryNumberGeneratorController implements LotteryNumberGeneratorApi, LotteryNumberGeneratorSwaggerApi {

    private LotteryNumberGenerator lotteryNumberGenerator;

    LotteryNumberGeneratorController(LotteryNumberGenerator lotteryNumberGenerator) {
        this.lotteryNumberGenerator = lotteryNumberGenerator;
    }

    @Override
    public SortedSet<Integer> generateRandom(LotteryType lotteryType) {
        return lotteryNumberGenerator.generate(lotteryType.getQuantity(), lotteryType.getPool());
    }

    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LotteryType.class, new LotteryTypeConverter());
    }
}
