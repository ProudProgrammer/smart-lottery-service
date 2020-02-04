package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExperimentalFiveOutOfNinetyNumberGeneratorTest {

    private static final int NUMBER_OF_PARTITIONS = 5;
    private static final int SET_OF_NUMBERS = 90;

    @InjectMocks
    private ExperimentalFiveOutOfNinetyNumberGenerator underTest;

    @Mock
    private Random random;

    @Mock
    private PartitionGenerator partitionGenerator;

    @Mock
    private SimpleNumberGenerator simpleNumberGenerator;

    @Mock
    private EvenOddNumberGenerator evenOddNumberGenerator;

    @Test
    void testGenerateShouldReturnSetOf3EvenAnd2OddNumbersFrom3PartitionsWith311FormationWhenEvenNumbers3AndUsedPartitions3() {
        // GIVEN
        int evenNumbers = 3;
        int usedPartitions = 3;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(2, 4, 6, 19, 37));
        List<Partition> expectedPartitions = List.of(
                new Partition(3, 1, 18),
                new Partition(1, 19, 36),
                new Partition(1, 37, 54));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generate(3, 1, 18)).thenReturn(new TreeSet<>(List.of(2, 4, 6)));
        when(simpleNumberGenerator.generate(1, 19, 36)).thenReturn(new TreeSet<>(List.of(19)));
        when(simpleNumberGenerator.generate(1, 37, 54)).thenReturn(new TreeSet<>(List.of(37)));

        // WHEN
        var result = underTest.generate();

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf3EvenAnd2OddNumbersFrom3PartitionsWith221FormationWhenEvenNumbers3AndUsedPartitions3() {
        // GIVEN
        int evenNumbers = 3;
        int usedPartitions = 3;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(21, 34, 60, 61, 90));
        List<Partition> expectedPartitions = List.of(
                new Partition(2, 19, 36),
                new Partition(2, 55, 72),
                new Partition(1, 73, 90));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generate(2, 19, 36)).thenReturn(new TreeSet<>(List.of(21, 34)));
        when(simpleNumberGenerator.generate(2, 55, 72)).thenReturn(new TreeSet<>(List.of(60, 61)));
        when(simpleNumberGenerator.generate(1, 73, 90)).thenReturn(new TreeSet<>(List.of(90)));

        // WHEN
        var result = underTest.generate();

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf2EvenAnd3OddNumbersFrom3PartitionsWith113FormationWhenEvenNumbers2AndUsedPartitions3() {
        // GIVEN
        int evenNumbers = 2;
        int usedPartitions = 3;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(41, 71, 76, 78, 79));
        List<Partition> expectedPartitions = List.of(
                new Partition(1, 37, 54),
                new Partition(1, 55, 72),
                new Partition(3, 73, 90));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generate(1, 37, 54)).thenReturn(new TreeSet<>(List.of(41)));
        when(simpleNumberGenerator.generate(1, 55, 72)).thenReturn(new TreeSet<>(List.of(71)));
        when(simpleNumberGenerator.generate(3, 73, 90)).thenReturn(new TreeSet<>(List.of(76, 78, 79)));

        // WHEN
        var result = underTest.generate();

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf2EvenAnd3OddNumbersFrom4PartitionsWith1211FormationWhenEvenNumbers2AndUsedPartitions4() {
        // GIVEN
        int evenNumbers = 2;
        int usedPartitions = 4;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(20, 41, 55, 72, 87));
        List<Partition> expectedPartitions = List.of(
                new Partition(1, 37, 54),
                new Partition(2, 55, 72),
                new Partition(1, 19, 36),
                new Partition(1, 73, 90));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generate(1, 37, 54)).thenReturn(new TreeSet<>(List.of(41)));
        when(simpleNumberGenerator.generate(2, 55, 72)).thenReturn(new TreeSet<>(List.of(55, 72)));
        when(simpleNumberGenerator.generate(1, 19, 36)).thenReturn(new TreeSet<>(List.of(20)));
        when(simpleNumberGenerator.generate(1, 73, 90)).thenReturn(new TreeSet<>(List.of(87)));

        // WHEN
        var result = underTest.generate();

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf3EvenAnd2OddNumbersFrom4PartitionsWith1112FormationWhenEvenNumbers3AndUsedPartitions4AndNeedEvenCorrection() {
        // GIVEN
        int evenNumbers = 3;
        int usedPartitions = 4;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(39, 61, 20, 74, 76));
        List<Partition> expectedPartitions = List.of(
                new Partition(1, 37, 54),
                new Partition(1, 55, 72),
                new Partition(1, 19, 36),
                new Partition(2, 73, 90));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generate(1, 37, 54)).thenReturn(new TreeSet<>(List.of(39)));
        when(simpleNumberGenerator.generate(1, 55, 72)).thenReturn(new TreeSet<>(List.of(61)));
        when(simpleNumberGenerator.generate(1, 19, 36)).thenReturn(new TreeSet<>(List.of(21)));
        when(simpleNumberGenerator.generate(2, 73, 90)).thenReturn(new TreeSet<>(List.of(75, 77)));
        when(evenOddNumberGenerator.generateEvenNumber(19, 36, new TreeSet<>(List.of(39, 61)))).thenReturn(20);
        when(evenOddNumberGenerator.generateEvenNumber(73, 90, new TreeSet<>(List.of(39, 61, 20)))).thenReturn(74);
        when(evenOddNumberGenerator.generateEvenNumber(73, 90, new TreeSet<>(List.of(39, 61, 20, 74)))).thenReturn(76);

        // WHEN
        var result = underTest.generate();

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf3EvenAnd2OddNumbersFrom4PartitionsWith1112FormationWhenEvenNumbers3AndUsedPartitions4AndNeedOddCorrection() {
        // GIVEN
        int evenNumbers = 3;
        int usedPartitions = 4;
        SortedSet<Integer> expectedResult = new TreeSet<>(List.of(38, 62, 22, 85, 87));
        List<Partition> expectedPartitions = List.of(
                new Partition(1, 37, 54),
                new Partition(1, 55, 72),
                new Partition(1, 19, 36),
                new Partition(2, 73, 90));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generate(1, 37, 54)).thenReturn(new TreeSet<>(List.of(38)));
        when(simpleNumberGenerator.generate(1, 55, 72)).thenReturn(new TreeSet<>(List.of(62)));
        when(simpleNumberGenerator.generate(1, 19, 36)).thenReturn(new TreeSet<>(List.of(22)));
        when(simpleNumberGenerator.generate(2, 73, 90)).thenReturn(new TreeSet<>(List.of(76, 78)));
        when(evenOddNumberGenerator.generateOddNumber(73, 90, new TreeSet<>(List.of(38, 62, 22)))).thenReturn(85);
        when(evenOddNumberGenerator.generateOddNumber(73, 90, new TreeSet<>(List.of(38, 62, 22, 85)))).thenReturn(87);

        // WHEN
        var result = underTest.generate();

        // THEN
        assertEquals(expectedResult, result);
    }

    private void setEvenNumbersAndUsedPartitions(int evenNumbers, int usedPartitions) {
        if ((evenNumbers != 2 && evenNumbers != 3) || (usedPartitions != 3 && usedPartitions != 4)) {
            String msg = MessageFormat
                    .format("Even numbers must be 2 or 3 and used partitions must be 3 or 4. Even numbers: {0}, used partitions: {1}", evenNumbers, usedPartitions);
            throw new IllegalArgumentException(msg);
        }
        when(random.nextBoolean()).thenReturn(evenNumbers == 2).thenReturn(usedPartitions == 3);
    }
}
