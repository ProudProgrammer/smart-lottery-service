package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FormationGeneratorService {

    private final Random random;

    FormationGeneratorService(Random threadLocalRandom) {
        this.random = threadLocalRandom;
    }

    /**
     * Generate a formation like 3,1,1 where number of partitions are 5 and used partitions are 3.
     *
     * @param usedPartitions     is the number of used partitions
     * @param numberOfPartitions is the number of partitions
     * @return a list of numbers as formation
     * @throws IllegalArgumentException when used partitions is larger or equals than number of partitions
     */
    public List<Integer> generateFormation(int usedPartitions, int numberOfPartitions) throws IllegalArgumentException {
        validate(usedPartitions, numberOfPartitions);
        List<Integer> formation = new ArrayList<>();
        int availableDistributablePoints = numberOfPartitions;
        int maxDistributablePointsForOnePartition;
        int chosenPoints;
        for (int availablePartitions = usedPartitions; availablePartitions > 0; availablePartitions--) {
            chosenPoints = 1;
            maxDistributablePointsForOnePartition = availableDistributablePoints - availablePartitions + 1;
            if (maxDistributablePointsForOnePartition > 1) {
                chosenPoints = random.nextInt(maxDistributablePointsForOnePartition);
            }
            formation.add(chosenPoints);
            availableDistributablePoints -= chosenPoints;
        }
        return formation;
    }

    private void validate(int usedPartitions, int numberOfPartitions) throws IllegalArgumentException {
        if (numberOfPartitions <= usedPartitions) {
            String msg = MessageFormat.format("Number of partitions must be larger than used partitions. Number of partitions: {0}, Used partitions: {1}", numberOfPartitions, usedPartitions);
            throw new IllegalArgumentException(msg);
        }
    }
}
