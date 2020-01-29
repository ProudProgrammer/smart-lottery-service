package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import java.util.SortedSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorSwaggerApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.editor.GeneratorTypeEditor;
import org.gaborbalazs.smartplatform.lotteryservice.web.editor.LotteryTypeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LotteryNumberGeneratorController implements LotteryNumberGeneratorApi, LotteryNumberGeneratorSwaggerApi {

    private final LotteryNumberGenerator lotteryNumberGenerator;

    LotteryNumberGeneratorController(LotteryNumberGenerator lotteryNumberGenerator) {
        this.lotteryNumberGenerator = lotteryNumberGenerator;
    }

    @Override
    public SortedSet<Integer> generate(LotteryType lotteryType, GeneratorType generatorType) {
        return lotteryNumberGenerator.generate(lotteryType, generatorType);
    }

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize, GeneratorType generatorType) {
        return lotteryNumberGenerator.generate(quantity, poolSize, generatorType);
    }

    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LotteryType.class, new LotteryTypeEditor());
        webDataBinder.registerCustomEditor(GeneratorType.class, new GeneratorTypeEditor());
    }
}
