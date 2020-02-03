package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.springframework.stereotype.Component;

/**
 * Based on Szôke Szilvia's and Huzsvai László's study called Experience of the 60 years old Hungarian National Lottery (5/90).
 */
@Component
public class ExperimentalFiveOutOfNinetyNumberGenerator {

    private final Random random;
    private final PartitionGenerator partitionGenerator;
    private final SimpleNumberGenerator simpleNumberGenerator;
    private final EvenOddFilter evenOddFilter;
    private final ListShuffler listShuffler;

    ExperimentalFiveOutOfNinetyNumberGenerator(Random threadLocalRandom, PartitionGenerator partitionGenerator, SimpleNumberGenerator simpleNumberGenerator,
            EvenOddFilter evenOddFilter, ListShuffler listShuffler) {
        this.random = threadLocalRandom;
        this.partitionGenerator = partitionGenerator;
        this.simpleNumberGenerator = simpleNumberGenerator;
        this.evenOddFilter = evenOddFilter;
        this.listShuffler = listShuffler;
    }

    /**
     * Improve odds (5/90):
     * - 2 or 3 even numbers from the 5
     * - 3 or 4 partition from the 5
     *
     * @return set of drawn numbers
     */
    public SortedSet<Integer> generate() {
        SortedSet<Integer> result = new TreeSet<>();
        int evenNumbers = random.nextBoolean() ? 2 : 3;
        int usedPartitions = random.nextBoolean() ? 3 : 4;
        int numberOfPartitions = 5;
        List<Partition> partitions = partitionGenerator.generate(usedPartitions, numberOfPartitions, 90);
        for (Partition partition : partitions) {
            SortedSet<Integer> chosenNumbers = simpleNumberGenerator.generate(partition.getOccurrence(), partition.getLowerLimit(), partition.getUpperLimit());
            for (Integer chosenNumber : chosenNumbers) {
                int properChosenNumber = getProperChosenNumber(chosenNumber, numberOfPartitions, evenNumbers, partition, result);
                result.add(properChosenNumber);
                evenNumbers = properChosenNumber % 2 == 0 ? --evenNumbers : evenNumbers;
                numberOfPartitions--;
            }
        }
        return result;
    }

    private int getProperChosenNumber(int chosenNumber, int numberOfPartitions, int evenNumbers, Partition partition, SortedSet<Integer> result) {
        List<Integer> filteredNumbers = null;
        if (numberOfPartitions == evenNumbers && chosenNumber % 2 != 0) {
            filteredNumbers = evenOddFilter.getEvenNumbers(partition.getLowerLimit(), partition.getUpperLimit());
        } else if (evenNumbers == 0 && chosenNumber % 2 == 0) {
            filteredNumbers = evenOddFilter.getOddNumbers(partition.getLowerLimit(), partition.getUpperLimit());
        }
        if (filteredNumbers != null) {
            filteredNumbers.stream().filter(result::contains).forEach(filteredNumbers::remove);
            filteredNumbers = listShuffler.shuffle(filteredNumbers);
            chosenNumber = filteredNumbers.get(random.nextInt(filteredNumbers.size()));
        }
        return chosenNumber;
    }
}
