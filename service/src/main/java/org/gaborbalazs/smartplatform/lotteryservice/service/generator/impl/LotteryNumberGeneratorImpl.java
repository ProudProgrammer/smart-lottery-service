package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import java.util.SortedSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

@Service
class LotteryNumberGeneratorImpl implements LotteryNumberGenerator {

    private final LotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy;
    private final LotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy;

    LotteryNumberGeneratorImpl(LotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy, LotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy) {
        this.defaultLotteryNumberGeneratorStrategy = defaultLotteryNumberGeneratorStrategy;
        this.experimentalLotteryNumberGeneratorStrategy = experimentalLotteryNumberGeneratorStrategy;
    }

    @Override
    public SortedSet<Integer> generate(LotteryType lotteryType, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException {
        return generate(lotteryType.getQuantity(), lotteryType.getPool(), generatorType);
    }

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException {
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
}
