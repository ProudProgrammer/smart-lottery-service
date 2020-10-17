package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.QuantityAndPoolSize;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LotteryNumberGeneratorController implements LotteryNumberGeneratorApi {

    private final LotteryNumberGenerator lotteryNumberGenerator;

    LotteryNumberGeneratorController(LotteryNumberGenerator lotteryNumberGenerator) {
        this.lotteryNumberGenerator = lotteryNumberGenerator;
    }

    @Override
    public GeneratedNumbers generate(LotteryType lotteryType, GeneratorType generatorType) throws UnsupportedOperationException {
        return lotteryNumberGenerator.generate(lotteryType, generatorType);
    }

    @Override
    public GeneratedNumbers generate(QuantityAndPoolSize quantityAndPoolSize, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException {
        return lotteryNumberGenerator.generate(quantityAndPoolSize.getQuantity(), quantityAndPoolSize.getPoolSize(), generatorType);
    }
}
