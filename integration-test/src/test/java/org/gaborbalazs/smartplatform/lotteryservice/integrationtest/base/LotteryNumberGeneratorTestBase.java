package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LotteryNumberGeneratorTestBase extends TestBase {

    private static Stream<Arguments> provideUsedPartitionsAndSortedSetAsDrawnNumbers() {
        return Stream.of(
                Arguments.of(1, new TreeSet<>(List.of(1, 2, 3, 4, 5))),
                Arguments.of(1, new TreeSet<>(List.of(19, 20, 27, 35, 36))),
                Arguments.of(2, new TreeSet<>(List.of(1, 18, 19, 20, 36))),
                Arguments.of(3, new TreeSet<>(List.of(19, 37, 38, 54, 73))),
                Arguments.of(4, new TreeSet<>(List.of(18, 37, 38, 72, 73))),
                Arguments.of(5, new TreeSet<>(List.of(1, 19, 37, 55, 73))),
                Arguments.of(5, new TreeSet<>(List.of(18, 36, 54, 72, 90)))
        );
    }

    /**
     * Test to check countUsedPartitions method works well.
     *
     * @param expectedUsedPartitions is the expected value
     * @param sortedSet              is the drawn numbers
     */
    @ParameterizedTest
    @MethodSource("provideUsedPartitionsAndSortedSetAsDrawnNumbers")
    void testCountUsedPartitions(int expectedUsedPartitions, SortedSet<Integer> sortedSet) {
        // GIVEN
        // WHEN
        var result = countUsedPartitions(sortedSet);

        // THEN
        assertEquals(expectedUsedPartitions, result);
    }

    protected String getLotteryNumberGeneratorUrl(LotteryType lotteryType) {
        return "/lottery/" + lotteryType.getPathVariableName() + "/numbers";
    }

    protected String getLotteryNumberGeneratorUrl(LotteryType lotteryType, GeneratorType generatorType) {
        return "/lottery/" + lotteryType.getPathVariableName() + "/numbers?generatorType=" + generatorType.getValue();
    }

    protected String getLotteryNumberGeneratorUrl(int quantity, int poolSize) {
        return "/lottery/numbers?quantity=" + quantity + "&poolSize=" + poolSize;
    }

    protected String getLotteryNumberGeneratorUrl(int quantity, int poolSize, GeneratorType generatorType) {
        return "/lottery/numbers?quantity=" + quantity + "&poolSize=" + poolSize + "&generatorType=" + generatorType.getValue();
    }

    protected int countEvenNumbers(SortedSet<Integer> drawnNumbers) {
        return (int) drawnNumbers.stream().filter(number -> number % 2 == 0).count();
    }

    protected int countUsedPartitions(SortedSet<Integer> drawnNumbers) {
        int[] usedPartitions = {0, 0, 0, 0, 0};
        for (Integer drawnNumber : drawnNumbers) {
            int partition = ((int) Math.ceil(((double) drawnNumber / 18))) - 1;
            usedPartitions[partition] = 1;
        }
        return (int) Arrays.stream(usedPartitions).filter(usedPartition -> usedPartition == 1).count();
    }
}
