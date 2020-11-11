package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
class FormationGenerator {

    private static final String MSG_USED_PARTITIONS_MUST_BE_LARGER_THAN_1 = "validate.generator.usedPartitionsMustBeLargerThan1";
    private static final String MSG_NUMBER_OF_PARTITIONS_MUST_BE_LARGER_THAN_USED = "validate.generator.numberOfPartitionsMustBeLargerThanUsed";

    private final Random random;
    private final MessageResolver messageResolver;
    private final Logger logger;

    public FormationGenerator(Random random, MessageResolver messageResolver, Logger logger) {
        this.random = random;
        this.messageResolver = messageResolver;
        this.logger = logger;
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
            logger.error(messageResolver.withUSLocale(MSG_USED_PARTITIONS_MUST_BE_LARGER_THAN_1, usedPartitions));
            throw new IllegalArgumentException(messageResolver.withRequestLocale(MSG_USED_PARTITIONS_MUST_BE_LARGER_THAN_1, usedPartitions));
        } else if (numberOfPartitions <= usedPartitions) {
            logger.error(messageResolver.withUSLocale(MSG_NUMBER_OF_PARTITIONS_MUST_BE_LARGER_THAN_USED, numberOfPartitions, usedPartitions));
            throw new IllegalArgumentException(messageResolver.withRequestLocale(MSG_NUMBER_OF_PARTITIONS_MUST_BE_LARGER_THAN_USED, numberOfPartitions, usedPartitions));
        }
    }
}
