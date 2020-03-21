package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.DrawnNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.MessageFactory;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

@Service
class LotteryNumberGeneratorFacade implements LotteryNumberGenerator {

    private final LotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy;
    private final LotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy;
    private final MessageFactory messageFactory;

    LotteryNumberGeneratorFacade(LotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy, LotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy,
                                 MessageFactory messageFactory) {
        this.defaultLotteryNumberGeneratorStrategy = defaultLotteryNumberGeneratorStrategy;
        this.experimentalLotteryNumberGeneratorStrategy = experimentalLotteryNumberGeneratorStrategy;
        this.messageFactory = messageFactory;
    }

    @Override
    public DrawnNumbers generate(LotteryType lotteryType, GeneratorType generatorType) throws UnsupportedOperationException {
        return getLotteryNumberGeneratorStrategy(generatorType).generate(lotteryType.getQuantity(), lotteryType.getPool());
    }

    @Override
    public DrawnNumbers generate(int quantity, int poolSize, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException {
        validate(quantity, poolSize);
        return getLotteryNumberGeneratorStrategy(generatorType).generate(quantity, poolSize);
    }

    private LotteryNumberGeneratorStrategy getLotteryNumberGeneratorStrategy(GeneratorType generatorType) {
        LotteryNumberGeneratorStrategy lotteryNumberGeneratorStrategy;
        if (generatorType == GeneratorType.EXPERIMENTAL) {
            lotteryNumberGeneratorStrategy = experimentalLotteryNumberGeneratorStrategy;
        } else {
            lotteryNumberGeneratorStrategy = defaultLotteryNumberGeneratorStrategy;
        }
        return lotteryNumberGeneratorStrategy;
    }

    private void validate(int quantity, int poolSize) throws IllegalArgumentException {
        if (quantity == 0) {
            throw new IllegalArgumentException("Quantity must not be 0.");
        } else if (poolSize > 1000) {
            String msg = messageFactory.create("Pool size must not be larger than {0}. Pool size: {1}", 1000, poolSize);
            throw new IllegalArgumentException(msg);
        } else if (poolSize <= quantity) {
            String msg = messageFactory.create("Pool size must be larger than quantity. Quantity: {0}, Pool size: {1}", quantity, poolSize);
            throw new IllegalArgumentException(msg);
        }
    }
}
