package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EvenOddFilterTest {

    @InjectMocks
    private EvenOddFilter underTest;

    @Test
    void testGetEvenNumbersShouldThrowExceptionWhenUpperLimitLessOrEqualThanLowerLimit() {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        int lowerLimit = 19;
        int upperLimit = 19;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.getEvenNumbers(lowerLimit, upperLimit));
    }

    @Test
    void testGetOddNumbersShouldThrowExceptionWhenUpperLimitLessOrEqualThanLowerLimit() {
        // GIVEN
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;
        int lowerLimit = 20;
        int upperLimit = 19;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.getOddNumbers(lowerLimit, upperLimit));
    }

    @Test
    void testGetEvenNumbersShouldReturnListOfEvenNumbersBetweenLowerLimitAndUpperLimit() {
        // GIVEN
        int lowerLimit = 19;
        int upperLimit = 36;
        List<Integer> expectedResult = List.of(20, 22, 24, 26, 28, 30, 32, 34, 36);

        // WHEN
        var result = underTest.getEvenNumbers(lowerLimit, upperLimit);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOddNumbersShouldReturnListOfOddNumbersBetweenLowerLimitAndUpperLimit() {
        // GIVEN
        int lowerLimit = 19;
        int upperLimit = 36;
        List<Integer> expectedResult = List.of(19, 21, 23, 25, 27, 29, 31, 33, 35);

        // WHEN
        var result = underTest.getOddNumbers(lowerLimit, upperLimit);

        // THEN
        assertEquals(expectedResult, result);
    }
}
