package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.SortedSet;

@Service
class LotteryNumberGeneratorFacade implements LotteryNumberGenerator {

    private final LotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy;
    private final LotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy;

    LotteryNumberGeneratorFacade(LotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy, LotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy) {
        this.defaultLotteryNumberGeneratorStrategy = defaultLotteryNumberGeneratorStrategy;
        this.experimentalLotteryNumberGeneratorStrategy = experimentalLotteryNumberGeneratorStrategy;
    }

    @Override
    public SortedSet<Integer> generate(LotteryType lotteryType, GeneratorType generatorType) throws UnsupportedOperationException {
        return getLotteryNumberGeneratorStrategy(generatorType).generate(lotteryType.getQuantity(), lotteryType.getPool());
    }

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException {
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
            String msg = MessageFormat.format("Pool size must not be larger than 1 000. Pool size: {0}", poolSize);
            throw new IllegalArgumentException(msg);
        } else if (poolSize <= quantity) {
            String msg = MessageFormat.format("Pool size must be larger than quantity. Quantity: {0}, Pool size: {1}", quantity, poolSize);
            throw new IllegalArgumentException(msg);
        }
    }
}
