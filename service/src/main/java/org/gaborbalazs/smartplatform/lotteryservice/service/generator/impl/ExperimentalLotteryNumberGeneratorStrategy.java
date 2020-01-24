package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.SortedSet;

@Service
public class ExperimentalLotteryNumberGeneratorStrategy implements LotteryNumberGeneratorStrategy {

    private final Random random;

    ExperimentalLotteryNumberGeneratorStrategy(Random threadLocalRandom) {
        this.random = threadLocalRandom;
    }

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize) throws IllegalArgumentException {
        return null;
    }
}
