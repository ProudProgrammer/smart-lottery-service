package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.common.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.common.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorSwaggerApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.editor.GeneratorTypeEditor;
import org.gaborbalazs.smartplatform.lotteryservice.web.editor.LotteryTypeEditor;
import org.gaborbalazs.smartplatform.lotteryservice.web.facade.LotteryNumberGeneratorFacade;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import java.util.SortedSet;

@RestController
class LotteryNumberGeneratorController implements LotteryNumberGeneratorApi, LotteryNumberGeneratorSwaggerApi {

    private LotteryNumberGeneratorFacade lotteryNumberGeneratorFacade;

    LotteryNumberGeneratorController(LotteryNumberGeneratorFacade lotteryNumberGeneratorFacade) {
        this.lotteryNumberGeneratorFacade = lotteryNumberGeneratorFacade;
    }

    @Override
    public SortedSet<Integer> generate(LotteryType lotteryType, GeneratorType generatorType) {
        return lotteryNumberGeneratorFacade.generate(lotteryType, generatorType);
    }

    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LotteryType.class, new LotteryTypeEditor());
        webDataBinder.registerCustomEditor(GeneratorType.class, new GeneratorTypeEditor());
    }
}
