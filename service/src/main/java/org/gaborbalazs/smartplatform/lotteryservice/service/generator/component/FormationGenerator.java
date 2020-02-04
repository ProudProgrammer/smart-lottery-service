package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
class FormationGenerator {

    private final Random random;

    FormationGenerator(Random threadLocalRandom) {
        this.random = threadLocalRandom;
    }

    /**
     * Generate a formation list with as many elements as used partitions. Sum of elements is number of partitions.
     * Example: 3,1,1 where number of partitions are 5 and used partitions are 3.
     *
     * @param usedPartitions     is the number of used partitions
     * @param numberOfPartitions is the number of partitions
     * @return a list of numbers as formation
     * @throws IllegalArgumentException if used partitions is larger or equals than number of partitions
     * @throws IllegalArgumentException if used partitions is smaller or equals then 1
     */
    List<Integer> generate(int usedPartitions, int numberOfPartitions) throws IllegalArgumentException {
        validate(usedPartitions, numberOfPartitions);
        List<Integer> formation = new ArrayList<>();
        int availableDistributablePoints = numberOfPartitions;
        int maxDistributablePointsForOnePartition;
        int chosenPoints;
        for (int availablePartitions = usedPartitions; availablePartitions > 0; availablePartitions--) {
            chosenPoints = 1;
            maxDistributablePointsForOnePartition = availableDistributablePoints - availablePartitions + 1;
            if (availablePartitions == 1) {
                chosenPoints = maxDistributablePointsForOnePartition;
            } else if (maxDistributablePointsForOnePartition > 1) {
                chosenPoints = random.nextInt(maxDistributablePointsForOnePartition) + 1;
            }
            formation.add(chosenPoints);
            availableDistributablePoints -= chosenPoints;
        }
        return formation;
    }

    private void validate(int usedPartitions, int numberOfPartitions) throws IllegalArgumentException {
        if (usedPartitions <= 1) {
            String msg = MessageFormat
                    .format("Used partitions must be larger than 1. Used partitions: {0}", usedPartitions);
            throw new IllegalArgumentException(msg);
        } else if (numberOfPartitions <= usedPartitions) {
            String msg = MessageFormat
                    .format("Number of partitions must be larger than used partitions. Number of partitions: {0}, used partitions: {1}", numberOfPartitions, usedPartitions);
            throw new IllegalArgumentException(msg);
        }
    }
}
