package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PartitionServiceTest {

    private PartitionService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PartitionService();
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
        assertThrows(expectedExceptionClass, () -> underTest.createPartitions(usedPartitions, numberOfPartitions, setOfNumbers));
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
        assertThrows(expectedExceptionClass, () -> underTest.createPartitions(usedPartitions, numberOfPartitions, setOfNumbers));
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
        assertThrows(expectedExceptionClass, () -> underTest.createPartitions(usedPartitions, numberOfPartitions, setOfNumbers));
    }
}