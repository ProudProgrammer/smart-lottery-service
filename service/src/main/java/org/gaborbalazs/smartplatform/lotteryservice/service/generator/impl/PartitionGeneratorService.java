package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.springframework.stereotype.Component;

@Component
public class PartitionGeneratorService {

    private final Random random;
    private final NumberGeneratorService numberGeneratorService;

    PartitionGeneratorService(Random threadLocalRandom, NumberGeneratorService numberGeneratorService) {
        this.random = threadLocalRandom;
        this.numberGeneratorService = numberGeneratorService;
    }

    public List<Partition> generatePartitions(int usedPartitions, int numberOfPartitions, int setOfNumbers) {
        if (setOfNumbers <= numberOfPartitions || numberOfPartitions <= usedPartitions || setOfNumbers % numberOfPartitions != 0) {
            String msg = MessageFormat
                    .format("Set of numbers must be larger than number of partitions and their division with remainder must be 0 and number of partitions must be larger than used partitions. Set of numbers: {0}, Number of partitions: {1}, Used partitions: {2}",
                            setOfNumbers, numberOfPartitions, usedPartitions);
            throw new IllegalArgumentException(msg);
        }
        List<Partition> partitions = new ArrayList<>();
        List<Integer> formation = generateFormation(usedPartitions, numberOfPartitions);
        SortedSet<Integer> chosenPartitions = numberGeneratorService.generate(usedPartitions, numberOfPartitions);
        int range = setOfNumbers / numberOfPartitions;
        int pointer = 0;
        int counter = 0;
        int lowerLimit;
        int upperLimit;
        for (int i = 0; i < numberOfPartitions; i++) {
            lowerLimit = ++pointer;
            pointer += range - 1;
            upperLimit = pointer;
            if (chosenPartitions.contains(i + 1)) {
                counter = formation.remove(random.nextInt(formation.size()));
            }
            partitions.add(new Partition(counter, lowerLimit, upperLimit));
        }
        return partitions;
    }

    private List<Integer> generateFormation(int usedPartitions, int numberOfPartitions) {
        List<List<Integer>> allFormation = allFormations();
        int chosenFormationIndex = random.nextInt(allFormation.size());
        return allFormation.get(chosenFormationIndex);
    }

    private List<List<Integer>> allFormations() {
        List<List<Integer>> allFormation = new ArrayList<>();
        return allFormation;
    }
}
