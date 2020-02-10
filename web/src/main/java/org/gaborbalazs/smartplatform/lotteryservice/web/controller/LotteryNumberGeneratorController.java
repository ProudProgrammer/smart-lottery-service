package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import java.util.SortedSet;

import javax.servlet.http.HttpServletResponse;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
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
    private final HttpServletResponse httpServletResponse;
    private final RequestContext requestContext;

    LotteryNumberGeneratorController(LotteryNumberGenerator lotteryNumberGenerator, HttpServletResponse httpServletResponse, RequestContext requestContext) {
        this.lotteryNumberGenerator = lotteryNumberGenerator;
        this.httpServletResponse = httpServletResponse;
        this.requestContext = requestContext;
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
        httpServletResponse.addHeader(HeaderParameterName.GENERATOR_TYPE.getHeaderName(), generatorType.getValue());
        httpServletResponse.addHeader(HeaderParameterName.LOCALE.getHeaderName(), requestContext.getLocale().getDisplayName());
    }

    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LotteryType.class, new LotteryTypeEditor());
        webDataBinder.registerCustomEditor(GeneratorType.class, new GeneratorTypeEditor());
    }
}
