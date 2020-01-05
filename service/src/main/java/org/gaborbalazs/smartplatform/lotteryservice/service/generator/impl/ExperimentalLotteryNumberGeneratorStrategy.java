package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

import java.util.SortedSet;

@Service
public class ExperimentalLotteryNumberGeneratorStrategy implements LotteryNumberGeneratorStrategy {

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize) throws IllegalArgumentException {
        return null;
    }
}
