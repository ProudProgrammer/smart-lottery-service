package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.gaborbalazs.smartplatform.lotteryservice.integrationtest.component.LotteryNumberGeneratorHeaderFactory;
import org.gaborbalazs.smartplatform.lotteryservice.integrationtest.component.LotteryNumberGeneratorUrlFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

public class LotteryNumberGeneratorTestBase extends TestBase {

    @Autowired
    protected LotteryNumberGeneratorUrlFactory lotteryNumberGeneratorUrlFactory;

    @Autowired
    protected LotteryNumberGeneratorHeaderFactory lotteryNumberGeneratorHeaderFactory;

    private static Stream<Arguments> provideUsedPartitionsAndDrawnNumbers() {
        return Stream.of(
                Arguments.of(1, List.of(1, 2, 3, 4, 5)),
                Arguments.of(1, List.of(19, 20, 27, 35, 36)),
                Arguments.of(2, List.of(1, 18, 19, 20, 36)),
                Arguments.of(3, List.of(19, 37, 38, 54, 73)),
                Arguments.of(4, List.of(18, 37, 38, 72, 73)),
                Arguments.of(5, List.of(1, 19, 37, 55, 73)),
                Arguments.of(5, List.of(18, 36, 54, 72, 90))
        );
    }

    /**
     * Test to check countUsedPartitions method works well.
     *
     * @param expectedUsedPartitions is the expected value
     * @param drawnNumbers           is the drawn numbers
     */
    @ParameterizedTest
    @MethodSource("provideUsedPartitionsAndDrawnNumbers")
    void testCountUsedPartitions(int expectedUsedPartitions, List<Integer> drawnNumbers) {
        // GIVEN
        // WHEN
        var result = countUsedPartitions(drawnNumbers);

        // THEN
        assertEquals(expectedUsedPartitions, result);
    }

    protected int countEvenNumbers(List<Integer> drawnNumbers) {
        return (int) drawnNumbers.stream().filter(number -> number % 2 == 0).count();
    }

    protected int countUsedPartitions(List<Integer> drawnNumbers) {
        int[] usedPartitions = {0, 0, 0, 0, 0};
        for (Integer drawnNumber : drawnNumbers) {
            int partition = ((int) Math.ceil(((double) drawnNumber / 18))) - 1;
            usedPartitions[partition] = 1;
        }
        return (int) Arrays.stream(usedPartitions).filter(usedPartition -> usedPartition == 1).count();
    }

    protected List<Integer> castToListOfIntegers(List<?> wildcardList) {
        List<Integer> parameterizedList = new ArrayList<>();
        wildcardList.stream().filter(e -> e instanceof Integer).map(e -> (Integer) e).forEach(parameterizedList::add);
        return parameterizedList;
    }
}
