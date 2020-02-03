package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExperimentalFiveOutOfNinetyNumberGeneratorTest {

    @InjectMocks
    private ExperimentalFiveOutOfNinetyNumberGenerator underTest;

    @Spy
    private Random random = ThreadLocalRandom.current();

    @Mock
    private PartitionGenerator partitionGenerator;

    @Mock
    private SimpleNumberGenerator simpleNumberGenerator;

    @Mock
    private EvenOddFilter evenOddFilter;

    @Mock
    private ListShuffler listShuffler;

    @Test
    void testGenerateShouldReturnSetOf3EvenNumbers2OddNumbersFrom3PartitionsWhenEvenNumbers3AndUsedPartitions3() {
        // GIVEN
        int usedPartitions = 3;
        int numberOfPartitions = 5;
        int setOfNumbers = 90;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(2, 4, 6, 19, 37));
        List<Partition> expectedPartitions = List.of(
                new Partition(3, 1, 18),
                new Partition(1, 19, 36),
                new Partition(1, 37, 54));
        when(random.nextBoolean()).thenReturn(false).thenReturn(true);
        when(partitionGenerator.generate(usedPartitions, numberOfPartitions, setOfNumbers)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generate(3, 1, 18)).thenReturn(new TreeSet<>(List.of(2, 4 ,6)));
        when(simpleNumberGenerator.generate(1, 19, 36)).thenReturn(new TreeSet<>(List.of(19)));
        when(simpleNumberGenerator.generate(1, 37, 54)).thenReturn(new TreeSet<>(List.of(37)));

        // WHEN
        var result = underTest.generate();

        // THEN
        assertEquals(expectedResult, result);
    }
}
