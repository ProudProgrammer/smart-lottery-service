package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Random;
import java.util.SortedSet;

@Service
public class ExperimentalLotteryNumberGeneratorStrategy implements LotteryNumberGeneratorStrategy {

    private final Random random;

    ExperimentalLotteryNumberGeneratorStrategy(Random threadLocalRandom) {
        this.random = threadLocalRandom;
    }

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize) throws IllegalArgumentException, UnsupportedOperationException {
        validate(quantity, poolSize);
        return null;
    }

    private void validate(int quantity, int poolSize) throws IllegalArgumentException, UnsupportedOperationException {
        if (poolSize <= quantity) {
            String msg = MessageFormat.format("Pool size must be larger than quantity! Quantity: {0}, pool size: {1}", quantity, poolSize);
            throw new IllegalArgumentException(msg);
        } else if (quantity != 5 && poolSize != 90) {
            String msg = MessageFormat.format("Quantity {0} and pool size {1} together is unsupported. Only quantity 5 and pool size 90 together is supported.", quantity, poolSize);
            throw new UnsupportedOperationException(msg);
        }
    }
}
