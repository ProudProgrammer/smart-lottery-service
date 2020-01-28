package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;

@Component
public class PartitionGeneratorService {

    private final Random random;
    private final NumberGeneratorService numberGeneratorService;
    private final FormationGeneratorService formationGeneratorService;

    PartitionGeneratorService(Random threadLocalRandom, NumberGeneratorService numberGeneratorService, FormationGeneratorService formationGeneratorService) {
        this.random = threadLocalRandom;
        this.numberGeneratorService = numberGeneratorService;
        this.formationGeneratorService = formationGeneratorService;
    }

    /**
     * Generate partitions with ranges and occurrence.
     *
     * @param usedPartitions     is the number of used partitions
     * @param numberOfPartitions is the number of partitions
     * @param setOfNumbers       the pool of numbers
     * @return list of partitions
     * @throws IllegalArgumentException when number of partitions are larger or equals than set of numbers or used partitions are larger or equals than number of partitions or set of numbers divided by number of partitions remainder is not 0
     */
    public List<Partition> generatePartitions(int usedPartitions, int numberOfPartitions, int setOfNumbers) throws IllegalArgumentException {
        validate(usedPartitions, numberOfPartitions, setOfNumbers);
        List<Partition> partitions = new ArrayList<>();
        List<Integer> formation = formationGeneratorService.generateFormation(usedPartitions, numberOfPartitions);
        SortedSet<Integer> chosenPartitions = numberGeneratorService.generate(usedPartitions, numberOfPartitions);
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

    private void validate(int usedPartitions, int numberOfPartitions, int setOfNumbers) throws IllegalArgumentException {
        if (setOfNumbers <= numberOfPartitions || numberOfPartitions <= usedPartitions || setOfNumbers % numberOfPartitions != 0) {
            String msg = MessageFormat
                    .format("Set of numbers must be larger than number of partitions and their division with remainder must be 0 and number of partitions must be larger than used partitions. Set of numbers: {0}, Number of partitions: {1}, Used partitions: {2}",
                            setOfNumbers, numberOfPartitions, usedPartitions);
            throw new IllegalArgumentException(msg);
        }
    }
}
