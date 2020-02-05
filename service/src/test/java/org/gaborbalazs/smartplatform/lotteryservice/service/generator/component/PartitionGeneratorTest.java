package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PartitionGeneratorTest {

    @InjectMocks
    private PartitionGenerator underTest;

    @Mock
    private Random random;

    @Mock
    private SimpleNumberGenerator simpleNumberGenerator;

    @Mock
    private FormationGenerator formationGenerator;

    @Mock
    private ListShuffler listShuffler;

    @ParameterizedTest
    @CsvSource( {"3,90,90", "3,91,90", "5,5,90", "6,5,90", "3,7,90",})
    void testGeneratePartitionsShouldThrowException(int usedPartitions, int numberOfPartitions, int setOfNumbers) {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generate(usedPartitions, numberOfPartitions, setOfNumbers));
    }

    @Test
    void testGenerateShouldReturn123PartitionsWith311FormationWhenUsedPartitions123With311Formation() {
        // GIVEN
        int usedPartitions = 3;
        int numberOfPartitions = 5;
        int setOfNumbers = 90;
        Partition expectedPartition1 = new Partition(3, 1, 18);
        Partition expectedPartition2 = new Partition(1, 19, 36);
        Partition expectedPartition3 = new Partition(1, 37, 54);
        int expectedResultSize = 3;
        SortedSet<Integer> expectedPartitions = new TreeSet<>(List.of(1, 2, 3));
        List<Integer> expectedFormation = new ArrayList<>(List.of(3, 1, 1));
        when(simpleNumberGenerator.generate(usedPartitions, numberOfPartitions)).thenReturn(expectedPartitions);
        when(formationGenerator.generate(usedPartitions, numberOfPartitions)).thenReturn(expectedFormation);
        when(listShuffler.shuffle(expectedFormation)).thenReturn(expectedFormation);
        when(random.nextInt(3)).thenReturn(0);
        when(random.nextInt(2)).thenReturn(0);

        // WHEN
        var result = underTest.generate(usedPartitions, numberOfPartitions, setOfNumbers);

        // THEN
        assertEquals(expectedResultSize, result.size());
        assertEquals(expectedPartition1, result.get(0));
        assertEquals(expectedPartition2, result.get(1));
        assertEquals(expectedPartition3, result.get(2));
    }

    @Test
    void testGenerateShouldReturn235PartitionsWith221FormationWhenUsedPartitions235With221Formation() {
        // GIVEN
        int usedPartitions = 3;
        int numberOfPartitions = 5;
        int setOfNumbers = 90;
        Partition expectedPartition1 = new Partition(2, 19, 36);
        Partition expectedPartition2 = new Partition(2, 37, 54);
        Partition expectedPartition3 = new Partition(1, 73, 90);
        int expectedResultSize = 3;
        SortedSet<Integer> expectedPartitions = new TreeSet<>(List.of(2, 3, 5));
        List<Integer> expectedFormation = new ArrayList<>(List.of(2, 2, 1));
        when(simpleNumberGenerator.generate(usedPartitions, numberOfPartitions)).thenReturn(expectedPartitions);
        when(formationGenerator.generate(usedPartitions, numberOfPartitions)).thenReturn(expectedFormation);
        when(listShuffler.shuffle(expectedFormation)).thenReturn(expectedFormation);
        when(random.nextInt(3)).thenReturn(0);
        when(random.nextInt(2)).thenReturn(0);

        // WHEN
        var result = underTest.generate(usedPartitions, numberOfPartitions, setOfNumbers);

        // THEN
        assertEquals(expectedResultSize, result.size());
        assertEquals(expectedPartition1, result.get(0));
        assertEquals(expectedPartition2, result.get(1));
        assertEquals(expectedPartition3, result.get(2));
    }

    @Test
    void testGenerateShouldReturn1234PartitionsWith2111FormationWhenUsedPartitions1234WithFormation2111() {
        // GIVEN
        int usedPartitions = 4;
        int numberOfPartitions = 5;
        int setOfNumbers = 90;
        Partition expectedPartition1 = new Partition(2, 1, 18);
        Partition expectedPartition2 = new Partition(1, 19, 36);
        Partition expectedPartition3 = new Partition(1, 37, 54);
        Partition expectedPartition4 = new Partition(1, 55, 72);
        int expectedResultSize = 4;
        SortedSet<Integer> expectedPartitions = new TreeSet<>(List.of(1, 2, 3, 4));
        List<Integer> expectedFormation = new ArrayList<>(List.of(2, 1, 1, 1));
        when(simpleNumberGenerator.generate(usedPartitions, numberOfPartitions)).thenReturn(expectedPartitions);
        when(formationGenerator.generate(usedPartitions, numberOfPartitions)).thenReturn(expectedFormation);
        when(listShuffler.shuffle(expectedFormation)).thenReturn(expectedFormation);
        when(random.nextInt(4)).thenReturn(0);
        when(random.nextInt(3)).thenReturn(0);
        when(random.nextInt(2)).thenReturn(0);

        // WHEN
        var result = underTest.generate(usedPartitions, numberOfPartitions, setOfNumbers);

        // THEN
        assertEquals(expectedResultSize, result.size());
        assertEquals(expectedPartition1, result.get(0));
        assertEquals(expectedPartition2, result.get(1));
        assertEquals(expectedPartition3, result.get(2));
        assertEquals(expectedPartition4, result.get(3));
    }
}