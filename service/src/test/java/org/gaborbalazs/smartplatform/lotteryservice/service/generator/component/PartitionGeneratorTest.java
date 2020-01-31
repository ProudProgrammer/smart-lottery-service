package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitionGeneratorTest {

    private PartitionGenerator underTest;
    private SimpleNumberGenerator simpleNumberGenerator;
    private FormationGenerator formationGenerator;

    @BeforeEach
    void setUp() {
        simpleNumberGenerator = mock(SimpleNumberGenerator.class);
        formationGenerator = mock(FormationGenerator.class);
        underTest = new PartitionGenerator(ThreadLocalRandom.current(), simpleNumberGenerator, formationGenerator);
    }

    @Test
    void createPartitionsShouldThrowExceptionWhenSetOfNumbersSmallerThenNumberOfPartitions() {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        int usedPartitions = 3;
        int numberOfPartitions = 5;
        int setOfNumbers = 4;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generatePartitions(usedPartitions, numberOfPartitions, setOfNumbers));
    }

    @Test
    void createPartitionsShouldThrowExceptionWhenNumberOfPartitionsSmallerThenUsedPartitions() {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        int usedPartitions = 6;
        int numberOfPartitions = 5;
        int setOfNumbers = 90;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generatePartitions(usedPartitions, numberOfPartitions, setOfNumbers));
    }

    @Test
    void createPartitionsShouldThrowExceptionWhenSetOfNumbersDividedByNumberOfPartitionsRemainderNotZero() {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        int usedPartitions = 4;
        int numberOfPartitions = 5;
        int setOfNumbers = 91;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.generatePartitions(usedPartitions, numberOfPartitions, setOfNumbers));
    }
}