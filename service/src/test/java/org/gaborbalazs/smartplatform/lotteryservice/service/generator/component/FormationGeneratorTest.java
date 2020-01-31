package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class FormationGeneratorTest {

    private FormationGenerator underTest;
    private Random random;

    @BeforeEach
    void setUp() {
        random = Mockito.spy(ThreadLocalRandom.current());
        underTest = new FormationGenerator(random);
    }

    @ParameterizedTest
    @CsvSource({"0,5", "1,5", "5,5", "6,5"})
    void generateFormationShouldThrowException(int usedPartitions, int numberOfPartitions) {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generateFormation(usedPartitions, numberOfPartitions));
    }

    @Test
    void generateFormationShouldReturnListContains311WhenUsedPartitions3AndNumberOfPartitions5() {
        // GIVEN
        int usedPartitions = 3;
        int numberOfPartitions = 5;
        List<Integer> expectedResult = List.of(3, 1, 1);
        when(random.nextInt(3)).thenReturn(2);

        // WHEN
        var result = underTest.generateFormation(usedPartitions, numberOfPartitions);

        // THEN
        assertEquals(usedPartitions, result.size());
        assertEquals(numberOfPartitions, result.stream().reduce(0, Integer::sum));
        assertEquals(expectedResult.get(0), result.get(0));
        assertEquals(expectedResult.get(1), result.get(1));
        assertEquals(expectedResult.get(2), result.get(2));
    }

    @Test
    void generateFormationShouldReturnListContains131WhenUsedPartitions3AndNumberOfPartitions5() {
        // GIVEN
        int usedPartitions = 3;
        int numberOfPartitions = 5;
        List<Integer> expectedResult = List.of(1, 3, 1);
        when(random.nextInt(3)).thenReturn(0).thenReturn(2);

        // WHEN
        var result = underTest.generateFormation(usedPartitions, numberOfPartitions);

        // THEN
        assertEquals(usedPartitions, result.size());
        assertEquals(numberOfPartitions, result.stream().reduce(0, Integer::sum));
        assertEquals(expectedResult.get(0), result.get(0));
        assertEquals(expectedResult.get(1), result.get(1));
        assertEquals(expectedResult.get(2), result.get(2));
    }

    @Test
    void generateFormationShouldReturnListContains113WhenUsedPartitions3AndNumberOfPartitions5() {
        // GIVEN
        int usedPartitions = 3;
        int numberOfPartitions = 5;
        List<Integer> expectedResult = List.of(1, 1, 3);
        when(random.nextInt(3)).thenReturn(0).thenReturn(0);

        // WHEN
        var result = underTest.generateFormation(usedPartitions, numberOfPartitions);

        // THEN
        assertEquals(usedPartitions, result.size());
        assertEquals(numberOfPartitions, result.stream().reduce(0, Integer::sum));
        assertEquals(expectedResult.get(0), result.get(0));
        assertEquals(expectedResult.get(1), result.get(1));
        assertEquals(expectedResult.get(2), result.get(2));
    }

    @Test
    void generateFormationShouldReturnListContains221WhenUsedPartitions3AndNumberOfPartitions5() {
        // GIVEN
        int usedPartitions = 3;
        int numberOfPartitions = 5;
        List<Integer> expectedResult = List.of(2, 2, 1);
        when(random.nextInt(3)).thenReturn(1);
        when(random.nextInt(2)).thenReturn(1);

        // WHEN
        var result = underTest.generateFormation(usedPartitions, numberOfPartitions);

        // THEN
        assertEquals(usedPartitions, result.size());
        assertEquals(numberOfPartitions, result.stream().reduce(0, Integer::sum));
        assertEquals(expectedResult.get(0), result.get(0));
        assertEquals(expectedResult.get(1), result.get(1));
        assertEquals(expectedResult.get(2), result.get(2));
    }

    @Test
    void generateFormationShouldReturnListContains212WhenUsedPartitions3AndNumberOfPartitions5() {
        // GIVEN
        int usedPartitions = 3;
        int numberOfPartitions = 5;
        List<Integer> expectedResult = List.of(2, 1, 2);
        when(random.nextInt(3)).thenReturn(1);
        when(random.nextInt(2)).thenReturn(0);

        // WHEN
        var result = underTest.generateFormation(usedPartitions, numberOfPartitions);

        // THEN
        assertEquals(usedPartitions, result.size());
        assertEquals(numberOfPartitions, result.stream().reduce(0, Integer::sum));
        assertEquals(expectedResult.get(0), result.get(0));
        assertEquals(expectedResult.get(1), result.get(1));
        assertEquals(expectedResult.get(2), result.get(2));
    }

    @Test
    void generateFormationShouldReturnListContains122WhenUsedPartitions3AndNumberOfPartitions5() {
        // GIVEN
        int usedPartitions = 3;
        int numberOfPartitions = 5;
        List<Integer> expectedResult = List.of(1, 2, 2);
        when(random.nextInt(3)).thenReturn(0).thenReturn(1);

        // WHEN
        var result = underTest.generateFormation(usedPartitions, numberOfPartitions);

        // THEN
        assertEquals(usedPartitions, result.size());
        assertEquals(numberOfPartitions, result.stream().reduce(0, Integer::sum));
        assertEquals(expectedResult.get(0), result.get(0));
        assertEquals(expectedResult.get(1), result.get(1));
        assertEquals(expectedResult.get(2), result.get(2));
    }

    @Test
    void generateFormationShouldReturnListContains41WhenUsedPartitions2AndNumberOfPartitions5() {
        // GIVEN
        int usedPartitions = 2;
        int numberOfPartitions = 5;
        List<Integer> expectedResult = List.of(4, 1);
        when(random.nextInt(4)).thenReturn(3);

        // WHEN
        var result = underTest.generateFormation(usedPartitions, numberOfPartitions);

        // THEN
        assertEquals(usedPartitions, result.size());
        assertEquals(numberOfPartitions, result.stream().reduce(0, Integer::sum));
        assertEquals(expectedResult.get(0), result.get(0));
        assertEquals(expectedResult.get(1), result.get(1));
    }

    @Test
    void generateFormationShouldReturnListContains14WhenUsedPartitions2AndNumberOfPartitions5() {
        // GIVEN
        int usedPartitions = 2;
        int numberOfPartitions = 5;
        List<Integer> expectedResult = List.of(1, 4);
        when(random.nextInt(4)).thenReturn(0);

        // WHEN
        var result = underTest.generateFormation(usedPartitions, numberOfPartitions);

        // THEN
        assertEquals(usedPartitions, result.size());
        assertEquals(numberOfPartitions, result.stream().reduce(0, Integer::sum));
        assertEquals(expectedResult.get(0), result.get(0));
        assertEquals(expectedResult.get(1), result.get(1));
    }
}
