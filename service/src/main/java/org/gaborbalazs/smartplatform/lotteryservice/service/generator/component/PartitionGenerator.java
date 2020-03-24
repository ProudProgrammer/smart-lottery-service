package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;

@Component
public class PartitionGenerator {

    private final Random random;
    private final SimpleNumberGenerator simpleNumberGenerator;
    private final FormationGenerator formationGenerator;
    private final ListShuffler listShuffler;
    private final MessageFactory messageFactory;
    private final Logger logger;

    PartitionGenerator(Random threadLocalRandom, SimpleNumberGenerator simpleNumberGenerator, FormationGenerator formationGenerator, ListShuffler listShuffler,
                       MessageFactory messageFactory, Logger logger) {
        this.random = threadLocalRandom;
        this.simpleNumberGenerator = simpleNumberGenerator;
        this.formationGenerator = formationGenerator;
        this.listShuffler = listShuffler;
        this.messageFactory = messageFactory;
        this.logger = logger;
    }

    /**
     * Generate list of {@link Partition} with ranges and occurrence(s).
     * Example: Input: (3,5,90), Output: List{Partition{3,1,18}, Partition{1,19,36}, Partition{1,37,54}}.
     * There are 3 partitions because of used partitions are 3. Sum of occurrences is 5 as number of partitions are 5.
     * The range is 18 as set of numbers (90) divided by number of partitions (5) is 18.
     *
     * @param usedPartitions     is the number of used partitions
     * @param numberOfPartitions is the number of partitions
     * @param setOfNumbers       the pool of numbers
     * @return list of partitions
     * @throws IllegalArgumentException if number of partitions are larger or equals than set of numbers
     * @throws IllegalArgumentException if used partitions are larger or equals than number of partitions
     * @throws IllegalArgumentException if set of numbers divided by number of partitions remainder is not 0
     */
    public List<Partition> generate(int usedPartitions, int numberOfPartitions, int setOfNumbers) throws IllegalArgumentException {
        validate(usedPartitions, numberOfPartitions, setOfNumbers);
        List<Partition> partitions = new ArrayList<>();
        List<Integer> formation = getFormation(usedPartitions, numberOfPartitions);
        SortedSet<Integer> chosenPartitions = simpleNumberGenerator.generate(usedPartitions, numberOfPartitions);
        int range = setOfNumbers / numberOfPartitions;
        int pointer = 0;
        for (int i = 0; i < numberOfPartitions; i++) {
            int lowerLimit = ++pointer;
            pointer += range - 1;
            int upperLimit = pointer;
            if (chosenPartitions.contains(i + 1)) {
                int occurrence = formation.remove(random.nextInt(formation.size()));
                partitions.add(new Partition(occurrence, lowerLimit, upperLimit));
            }
        }
        return partitions;
    }

    private List<Integer> getFormation(int usedPartitions, int numberOfPartitions) {
        List<Integer> formation = formationGenerator.generate(usedPartitions, numberOfPartitions);
        return listShuffler.shuffle(formation);
    }

    private void validate(int usedPartitions, int numberOfPartitions, int setOfNumbers) throws IllegalArgumentException {
        if (setOfNumbers <= numberOfPartitions || numberOfPartitions <= usedPartitions || setOfNumbers % numberOfPartitions != 0) {
            String msg = messageFactory
                    .create("Set of numbers must be larger than number of partitions and their division with remainder must be 0 and number of partitions must be larger than used partitions. Set of numbers: {0}, number of partitions: {1}, used partitions: {2}",
                            setOfNumbers, numberOfPartitions, usedPartitions);
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        }
    }
}
