package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.retrieve.iface.RetrieveDrawnNumbersService;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorSwaggerApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.editor.GeneratorTypeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import java.beans.PropertyEditorSupport;

@RestController
class LotteryNumberGeneratorController implements LotteryNumberGeneratorApi, LotteryNumberGeneratorSwaggerApi {

    private final LotteryNumberGenerator lotteryNumberGenerator;
    private final RequestContext requestContext;

    LotteryNumberGeneratorController(LotteryNumberGenerator lotteryNumberGenerator, RequestContext requestContext) {
        this.lotteryNumberGenerator = lotteryNumberGenerator;
        this.requestContext = requestContext;
    }

    @Override
    public GeneratedNumbers generate(LotteryType lotteryType, GeneratorType generatorType) throws UnsupportedOperationException {
        return lotteryNumberGenerator.generate(lotteryType, generatorType);
    }

    @Override
    public GeneratedNumbers generate(int quantity, int poolSize, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException {
        return lotteryNumberGenerator.generate(quantity, poolSize, generatorType);
    }
}
