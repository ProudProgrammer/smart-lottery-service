package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain.Partition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.text.MessageFormat;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExperimentalFiveOutOfNinetyNumberGeneratorTest {

    private static final int NUMBER_OF_PARTITIONS = 5;
    private static final int SET_OF_NUMBERS = 90;

    @InjectMocks
    private ExperimentalFiveOutOfNinetyNumberGenerator underTest;

    @Mock
    private Logger logger;

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
        List<Integer> expectedResult = List.of(2, 4, 6, 19, 37);
        List<Partition> expectedPartitions = List.of(
                new Partition(3, 1, 18),
                new Partition(1, 19, 36),
                new Partition(1, 37, 54));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(3, 1, 18)).thenReturn(List.of(2, 4, 6));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 19, 36)).thenReturn(List.of(19));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 37, 54)).thenReturn(List.of(37));

        // WHEN
        var result = underTest.generate();

        // THEN
        verify(logger, times(2)).debug(anyString(), any(Object.class));
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf3EvenAnd2OddNumbersFrom3PartitionsWith221FormationWhenEvenNumbers3AndUsedPartitions3() {
        // GIVEN
        int evenNumbers = 3;
        int usedPartitions = 3;
        List<Integer> expectedResult = List.of(21, 34, 60, 61, 90);
        List<Partition> expectedPartitions = List.of(
                new Partition(2, 19, 36),
                new Partition(2, 55, 72),
                new Partition(1, 73, 90));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(2, 19, 36)).thenReturn(List.of(21, 34));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(2, 55, 72)).thenReturn(List.of(60, 61));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 73, 90)).thenReturn((List.of(90)));

        // WHEN
        var result = underTest.generate();

        // THEN
        verify(logger, times(2)).debug(anyString(), any(Object.class));
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf2EvenAnd3OddNumbersFrom3PartitionsWith113FormationWhenEvenNumbers2AndUsedPartitions3() {
        // GIVEN
        int evenNumbers = 2;
        int usedPartitions = 3;
        List<Integer> expectedResult = List.of(41, 71, 76, 78, 79);
        List<Partition> expectedPartitions = List.of(
                new Partition(1, 37, 54),
                new Partition(1, 55, 72),
                new Partition(3, 73, 90));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 37, 54)).thenReturn(List.of(41));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 55, 72)).thenReturn(List.of(71));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(3, 73, 90)).thenReturn(List.of(76, 78, 79));

        // WHEN
        var result = underTest.generate();

        // THEN
        verify(logger, times(2)).debug(anyString(), any(Object.class));
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf2EvenAnd3OddNumbersFrom4PartitionsWith1211FormationWhenEvenNumbers2AndUsedPartitions4() {
        // GIVEN
        int evenNumbers = 2;
        int usedPartitions = 4;
        List<Integer> expectedResult = List.of(20, 41, 55, 72, 87);
        List<Partition> expectedPartitions = List.of(
                new Partition(1, 37, 54),
                new Partition(2, 55, 72),
                new Partition(1, 19, 36),
                new Partition(1, 73, 90));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 37, 54)).thenReturn(List.of(41));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(2, 55, 72)).thenReturn(List.of(55, 72));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 19, 36)).thenReturn(List.of(20));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 73, 90)).thenReturn(List.of(87));

        // WHEN
        var result = underTest.generate();

        // THEN
        verify(logger, times(2)).debug(anyString(), any(Object.class));
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf3EvenAnd2OddNumbersFrom4PartitionsWith1112FormationWhenEvenNumbers3AndUsedPartitions4AndNeedEvenCorrection() {
        // GIVEN
        int evenNumbers = 3;
        int usedPartitions = 4;
        List<Integer> expectedResult = List.of(20, 39, 61, 74, 76);
        List<Partition> expectedPartitions = List.of(
                new Partition(1, 37, 54),
                new Partition(1, 55, 72),
                new Partition(1, 19, 36),
                new Partition(2, 73, 90));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 37, 54)).thenReturn(List.of(39));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 55, 72)).thenReturn(List.of(61));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 19, 36)).thenReturn(List.of(21));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(2, 73, 90)).thenReturn(List.of(75, 77));
        when(evenOddNumberGenerator.generateEvenNumber(19, 36, List.of(39, 61))).thenReturn(20);
        when(evenOddNumberGenerator.generateEvenNumber(73, 90, List.of(39, 61, 20))).thenReturn(74);
        when(evenOddNumberGenerator.generateEvenNumber(73, 90, List.of(39, 61, 20, 74))).thenReturn(76);

        // WHEN
        var result = underTest.generate();

        // THEN
        verify(logger, times(2)).debug(anyString(), any(Object.class));
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf3EvenAnd2OddNumbersFrom4PartitionsWith1112FormationWhenEvenNumbers3AndUsedPartitions4AndNeedOddCorrection() {
        // GIVEN
        int evenNumbers = 3;
        int usedPartitions = 4;
        List<Integer> expectedResult = List.of(22, 38, 62, 85, 87);
        List<Partition> expectedPartitions = List.of(
                new Partition(1, 37, 54),
                new Partition(1, 55, 72),
                new Partition(1, 19, 36),
                new Partition(2, 73, 90));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 37, 54)).thenReturn(List.of(38));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 55, 72)).thenReturn(List.of(62));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 19, 36)).thenReturn(List.of(22));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(2, 73, 90)).thenReturn(List.of(76, 78));
        when(evenOddNumberGenerator.generateOddNumber(73, 90, List.of(38, 62, 22))).thenReturn(85);
        when(evenOddNumberGenerator.generateOddNumber(73, 90, List.of(38, 62, 22, 85))).thenReturn(87);

        // WHEN
        var result = underTest.generate();

        // THEN
        verify(logger, times(2)).debug(anyString(), any(Object.class));
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf3EvenAnd2OddNumbersFrom3PartitionsWith122FormationWhenEvenNumbers3AndUsedPartitions3AndNeedEvenCorrectionWithEvenNumberCollision() {
        // GIVEN
        int evenNumbers = 3;
        int usedPartitions = 3;
        List<Integer> expectedResult = List.of(36, 41, 53, 66, 68);
        List<Partition> expectedPartitions = List.of(
                new Partition(1, 19, 36),
                new Partition(2, 37, 54),
                new Partition(2, 55, 72));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 19, 36)).thenReturn(List.of(36));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(2, 37, 54)).thenReturn(List.of(41, 53));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(2, 55, 72)).thenReturn(List.of(59, 66));
        when(evenOddNumberGenerator.generateEvenNumber(55, 72, List.of(36, 41, 53))).thenReturn(66);
        when(evenOddNumberGenerator.generateEvenNumber(55, 72, List.of(36, 41, 53, 66))).thenReturn(68);

        // WHEN
        var result = underTest.generate();

        // THEN
        verify(logger, times(2)).debug(anyString(), any(Object.class));
        assertEquals(expectedResult, result);
    }

    @Test
    void testGenerateShouldReturnSetOf2EvenAnd3OddNumbersFrom3PartitionsWith122FormationWhenEvenNumbers2AndUsedPartitions3AndNeedOddCorrectionWithOddNumberCollision() {
        // GIVEN
        int evenNumbers = 2;
        int usedPartitions = 3;
        List<Integer> expectedResult = List.of(35, 42, 54, 67, 69);
        List<Partition> expectedPartitions = List.of(
                new Partition(1, 19, 36),
                new Partition(2, 37, 54),
                new Partition(2, 55, 72));
        setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions);
        when(partitionGenerator.generate(usedPartitions, NUMBER_OF_PARTITIONS, SET_OF_NUMBERS)).thenReturn(expectedPartitions);
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(1, 19, 36)).thenReturn(List.of(35));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(2, 37, 54)).thenReturn(List.of(42, 54));
        when(simpleNumberGenerator.generateUniqueNumbersFromSamePool(2, 55, 72)).thenReturn(List.of(58, 67));
        when(evenOddNumberGenerator.generateOddNumber(55, 72, List.of(35, 42, 54))).thenReturn(67);
        when(evenOddNumberGenerator.generateOddNumber(55, 72, List.of(35, 42, 54, 67))).thenReturn(69);

        // WHEN
        var result = underTest.generate();

        // THEN
        verify(logger, times(2)).debug(anyString(), any(Object.class));
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @CsvSource({"1,3", "2,5", "3,5", "1,1"})
    void testUnitTestShouldThrowException(int evenNumbers, int usedPartitions) {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> setEvenNumbersAndUsedPartitions(evenNumbers, usedPartitions));
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
