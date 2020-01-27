package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Based on Szôke Szilvia's and Huzsvai László's study called Experience of the 60 years old Hungarian National Lottery (5/90).
 */
@Service
public class ExperimentalLotteryNumberGeneratorStrategy implements LotteryNumberGeneratorStrategy {

    private final Random random;
    private final PartitionService partitionService;

    ExperimentalLotteryNumberGeneratorStrategy(Random threadLocalRandom, PartitionService partitionService) {
        this.random = threadLocalRandom;
        this.partitionService = partitionService;
    }

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize) throws IllegalArgumentException, UnsupportedOperationException {
        validate(quantity, poolSize);
        return callProperGenerate(quantity, poolSize);
    }

    private SortedSet<Integer> callProperGenerate(int quantity, int poolSize) throws UnsupportedOperationException {
        if (quantity == 5 && poolSize == 90) {
            return generate5from90();
        } else {
            String msg = MessageFormat.format("Quantity {0} and pool size {1} together is unsupported. Only quantity 5 and pool size 90 together is supported.", quantity, poolSize);
            throw new UnsupportedOperationException(msg);
        }
    }

    /**
     * Improve odds (5/90):
     * - 2 or 3 even numbers from the 5
     * - 3 or 4 partition from the 5
     *
     * @return set of drawn numbers
     */
    private SortedSet<Integer> generate5from90() {
        int evenNumbers = random.nextBoolean() ? 2 : 3;
        int usedPartitions = random.nextBoolean() ? 3 : 4;
        List<Partition> partitions = partitionService.createPartitions(usedPartitions, 5, 90);
        return new TreeSet<>(Set.of(1, 2, 3, 4, 5));
    }

    private void validate(int quantity, int poolSize) throws IllegalArgumentException {
        if (poolSize <= quantity) {
            String msg = MessageFormat.format("Pool size must be larger than quantity! Quantity: {0}, pool size: {1}", quantity, poolSize);
            throw new IllegalArgumentException(msg);
        }
    }
}
