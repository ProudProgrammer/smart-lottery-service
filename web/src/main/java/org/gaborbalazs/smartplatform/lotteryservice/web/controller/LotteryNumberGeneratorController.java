package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.LotteryTypeGeneratorTypeRequest;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.QuantityPoolSizeGeneratorTypeRequest;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LotteryNumberGeneratorController implements LotteryNumberGeneratorApi {

    private final LotteryNumberGenerator lotteryNumberGenerator;

    LotteryNumberGeneratorController(LotteryNumberGenerator lotteryNumberGenerator) {
        this.lotteryNumberGenerator = lotteryNumberGenerator;
    }

    @Override
    public GeneratedNumbers generate(LotteryTypeGeneratorTypeRequest request) throws UnsupportedOperationException {
        return lotteryNumberGenerator.generate(request.getLotteryType(), request.getGeneratorType());
    }

    @Override
    public GeneratedNumbers generate(QuantityPoolSizeGeneratorTypeRequest request) throws IllegalArgumentException, UnsupportedOperationException {
        return lotteryNumberGenerator.generate(request.getQuantity(), request.getPoolSize(), request.getGeneratorType());
    }
}
