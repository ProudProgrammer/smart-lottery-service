package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

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

import javax.servlet.http.HttpServletResponse;
import java.util.SortedSet;

@RestController
class LotteryNumberGeneratorController implements LotteryNumberGeneratorApi, LotteryNumberGeneratorSwaggerApi {

    private static final String GENERATOR_TYPE = "Generator-Type";

    private final LotteryNumberGenerator lotteryNumberGenerator;
    private final HttpServletResponse httpServletResponse;

    LotteryNumberGeneratorController(LotteryNumberGenerator lotteryNumberGenerator, HttpServletResponse httpServletResponse) {
        this.lotteryNumberGenerator = lotteryNumberGenerator;
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public SortedSet<Integer> generate(LotteryType lotteryType, GeneratorType generatorType) throws UnsupportedOperationException {
        setResponseHeaders(generatorType);
        return lotteryNumberGenerator.generate(lotteryType, generatorType);
    }

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException {
        setResponseHeaders(generatorType);
        return lotteryNumberGenerator.generate(quantity, poolSize, generatorType);
    }

    private void setResponseHeaders(GeneratorType generatorType) {
        httpServletResponse.addHeader(GENERATOR_TYPE, generatorType.getValue());
    }

    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LotteryType.class, new LotteryTypeEditor());
        webDataBinder.registerCustomEditor(GeneratorType.class, new GeneratorTypeEditor());
    }
}
