package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.SimpleNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

import java.util.SortedSet;

@Service("defaultLotteryNumberGeneratorStrategy")
public class DefaultLotteryNumberGenerator implements LotteryNumberGeneratorStrategy {

    private final SimpleNumberGenerator simpleNumberGenerator;

    DefaultLotteryNumberGenerator(SimpleNumberGenerator simpleNumberGenerator) {
        this.simpleNumberGenerator = simpleNumberGenerator;
    }

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize) throws IllegalArgumentException {
        return simpleNumberGenerator.generate(quantity, poolSize);
    }
}
