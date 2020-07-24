package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Based on Szôke Szilvia's and Huzsvai László's study called Experience of the 60 years old Hungarian National Lottery (5/90).
 */
@Component
public class ExperimentalFiveOutOfNinetyNumberGenerator {

    private final Logger logger;
    private final Random random;
    private final PartitionGenerator partitionGenerator;
    private final SimpleNumberGenerator simpleNumberGenerator;
    private final EvenOddNumberGenerator evenOddNumberGenerator;

    ExperimentalFiveOutOfNinetyNumberGenerator(Logger logger, Random threadLocalRandom, PartitionGenerator partitionGenerator, SimpleNumberGenerator simpleNumberGenerator,
                                               EvenOddNumberGenerator evenOddNumberGenerator) {
        this.logger = logger;
        this.random = threadLocalRandom;
        this.partitionGenerator = partitionGenerator;
        this.simpleNumberGenerator = simpleNumberGenerator;
        this.evenOddNumberGenerator = evenOddNumberGenerator;
    }

    /**
     * Improve odds (5/90):
     * - 2 or 3 even numbers from the 5
     * - 3 or 4 partition from the 5
     *
     * @return set of drawn numbers
     */
    public List<Integer> generate() {
        List<Integer> result = new ArrayList<>();
        int evenNumbers = getEvenNumbers();
        int usedPartitions = getUsedPartitions();
        int numberOfPartitions = 5;
        List<Partition> partitions = partitionGenerator.generate(usedPartitions, numberOfPartitions, 90);
        for (Partition partition : partitions) {
            List<Integer> chosenNumbers = simpleNumberGenerator.generateUniqueNumbersFromSamePool(partition.getOccurrence(), partition.getLowerLimit(), partition.getUpperLimit());
            for (Integer chosenNumber : chosenNumbers) {
                int properChosenNumber = getProperChosenNumber(chosenNumber, numberOfPartitions, evenNumbers, partition, result);
                result.add(properChosenNumber);
                evenNumbers = properChosenNumber % 2 == 0 ? --evenNumbers : evenNumbers;
                numberOfPartitions--;
            }
        }
        Collections.sort(result);
        return result;
    }

    private int getEvenNumbers() {
        int evenNumbers = random.nextBoolean() ? 2 : 3;
        logger.debug("Even numbers: {}.", evenNumbers);
        return evenNumbers;
    }

    private int getUsedPartitions() {
        int usedPartitions = random.nextBoolean() ? 3 : 4;
        logger.debug("Used partitions: {}.", usedPartitions);
        return usedPartitions;
    }

    private int getProperChosenNumber(int chosenNumber, int numberOfPartitions, int evenNumbers, Partition partition, List<Integer> result) {
        int properChosenNumber = chosenNumber;
        if (numberOfPartitions == evenNumbers && (chosenNumber % 2 != 0 || result.contains(chosenNumber))) {
            properChosenNumber = evenOddNumberGenerator.generateEvenNumber(partition.getLowerLimit(), partition.getUpperLimit(), result);
        } else if (evenNumbers == 0 && (chosenNumber % 2 == 0 || result.contains(chosenNumber))) {
            properChosenNumber = evenOddNumberGenerator.generateOddNumber(partition.getLowerLimit(), partition.getUpperLimit(), result);
        }
        return properChosenNumber;
    }
}
