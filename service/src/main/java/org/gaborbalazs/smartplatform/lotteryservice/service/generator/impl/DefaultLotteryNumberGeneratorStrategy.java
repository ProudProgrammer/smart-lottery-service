package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import java.util.SortedSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.NumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

@Service
public class DefaultLotteryNumberGeneratorStrategy implements LotteryNumberGeneratorStrategy {

    private final NumberGenerator numberGenerator;

    DefaultLotteryNumberGeneratorStrategy(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize) throws IllegalArgumentException {
        return numberGenerator.generate(quantity, poolSize);
    }
}
