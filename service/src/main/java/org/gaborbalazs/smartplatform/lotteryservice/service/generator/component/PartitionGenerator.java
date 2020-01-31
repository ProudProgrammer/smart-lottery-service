package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;

@Component
public class PartitionGenerator {

    private final Random random;
    private final SimpleNumberGenerator simpleNumberGenerator;
    private final FormationGenerator formationGenerator;

    PartitionGenerator(Random threadLocalRandom, SimpleNumberGenerator simpleNumberGenerator, FormationGenerator formationGenerator) {
        this.random = threadLocalRandom;
        this.simpleNumberGenerator = simpleNumberGenerator;
        this.formationGenerator = formationGenerator;
    }

    /**
     * Generate list of {@link Partition} with ranges and occurrence(s).
     * Example: Input: (3,5,90), Output: List{Partition{3,1,18}, Partition{1,19,36}, Partition{1,37,54}}.
     * There are 3 partitions because of used partitions are 3. Sum of occurrences is 5 because of number of partitions are 5.
     * The range is 18 because of set of numbers (90) divided with number of partitions (5) is 18.
     *
     * @param usedPartitions     is the number of used partitions
     * @param numberOfPartitions is the number of partitions
     * @param setOfNumbers       the pool of numbers
     * @return list of partitions
     * @throws IllegalArgumentException if number of partitions are larger or equals than set of numbers
     * @throws IllegalArgumentException if used partitions are larger or equals than number of partitions
     * @throws IllegalArgumentException if set of numbers divided by number of partitions remainder is not 0
     */
    public List<Partition> generatePartitions(int usedPartitions, int numberOfPartitions, int setOfNumbers) throws IllegalArgumentException {
        validate(usedPartitions, numberOfPartitions, setOfNumbers);
        List<Partition> partitions = new ArrayList<>();
        List<Integer> formation = getFormation(usedPartitions, numberOfPartitions);
        SortedSet<Integer> chosenPartitions = simpleNumberGenerator.generate(usedPartitions, numberOfPartitions);
        int range = setOfNumbers / numberOfPartitions;
        int pointer = 0;
        int occurrence = 0;
        int lowerLimit;
        int upperLimit;
        for (int i = 0; i < numberOfPartitions; i++) {
            lowerLimit = ++pointer;
            pointer += range - 1;
            upperLimit = pointer;
            if (chosenPartitions.contains(i + 1)) {
                occurrence = formation.remove(random.nextInt(formation.size()));
            }
            partitions.add(new Partition(occurrence, lowerLimit, upperLimit));
        }
        return partitions;
    }

    private List<Integer> getFormation(int usedPartitions, int numberOfPartitions) {
        List<Integer> formation = formationGenerator.generateFormation(usedPartitions, numberOfPartitions);
        Collections.shuffle(formation, random);
        return formation;
    }

    private void validate(int usedPartitions, int numberOfPartitions, int setOfNumbers) throws IllegalArgumentException {
        if (setOfNumbers <= numberOfPartitions || numberOfPartitions <= usedPartitions || setOfNumbers % numberOfPartitions != 0) {
            String msg = MessageFormat
                    .format("Set of numbers must be larger than number of partitions and their division with remainder must be 0 and number of partitions must be larger than used partitions. Set of numbers: {0}, Number of partitions: {1}, Used partitions: {2}",
                            setOfNumbers, numberOfPartitions, usedPartitions);
            throw new IllegalArgumentException(msg);
        }
    }
}