package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.ExperimentalFiveOutOfNinetyNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.MessageFactory;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.SortedSet;

@Service("experimentalLotteryNumberGeneratorStrategy")
public class ExperimentalLotteryNumberGenerator implements LotteryNumberGeneratorStrategy {

    private final ExperimentalFiveOutOfNinetyNumberGenerator experimentalFiveOutOfNinetyNumberGenerator;
    private final MessageFactory messageFactory;
    private final Logger logger;

    ExperimentalLotteryNumberGenerator(ExperimentalFiveOutOfNinetyNumberGenerator experimentalFiveOutOfNinetyNumberGenerator, MessageFactory messageFactory, Logger logger) {
        this.experimentalFiveOutOfNinetyNumberGenerator = experimentalFiveOutOfNinetyNumberGenerator;
        this.messageFactory = messageFactory;
        this.logger = logger;
    }

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize) throws IllegalArgumentException, UnsupportedOperationException {
        validate(quantity, poolSize);
        return callProperGenerator(quantity, poolSize);
    }

    private SortedSet<Integer> callProperGenerator(int quantity, int poolSize) throws UnsupportedOperationException {
        if (quantity == 5 && poolSize == 90) {
            return experimentalFiveOutOfNinetyNumberGenerator.generate();
        } else {
            String msg = messageFactory
                    .create("Quantity {0} and pool size {1} together are unsupported. Only quantity 5 and pool size 90 together are supported.", quantity, poolSize);
            logger.error(msg);
            throw new UnsupportedOperationException(msg);
        }
    }

    private void validate(int quantity, int poolSize) throws IllegalArgumentException {
        if (poolSize <= quantity) {
            String msg = messageFactory.create("Pool size must be larger than quantity! Quantity: {0}, pool size: {1}", quantity, poolSize);
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        }
    }
}
