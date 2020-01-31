package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Based on Szôke Szilvia's and Huzsvai László's study called Experience of the 60 years old Hungarian National Lottery (5/90).
 */
@Component
public class ExperimentalFiveOutOfNinetyNumberGenerator {

    private final Random random;
    private final PartitionGenerator partitionGenerator;

    ExperimentalFiveOutOfNinetyNumberGenerator(Random threadLocalRandom, PartitionGenerator partitionGenerator) {
        this.random = threadLocalRandom;
        this.partitionGenerator = partitionGenerator;
    }

    /**
     * Improve odds (5/90):
     * - 2 or 3 even numbers from the 5
     * - 3 or 4 partition from the 5
     *
     * @return set of drawn numbers
     */
    public SortedSet<Integer> generate() {
        int evenNumbers = random.nextBoolean() ? 2 : 3;
        int usedPartitions = random.nextBoolean() ? 3 : 4;
        List<Partition> partitions = partitionGenerator.generatePartitions(usedPartitions, 5, 90);
        // TODO
        return new TreeSet<>(Set.of(1, 2, 3, 4, 5));
    }
}
