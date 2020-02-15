package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.base;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LotteryNumberGeneratorTestBase extends TestBase {

    protected static Map<String, String> DEFAULT_REQUEST_HEADERS = Map.of(
            HeaderParameterName.REQUEST_ID.getHeaderName(), "test0000-0000-0000-0000-test00000000",
            HeaderParameterName.CONSUMER_NAME.getHeaderName(), "test",
            HeaderParameterName.LOCALE.getHeaderName(), "hu_HU");

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

    protected String getDefaultRequestIdHeader() {
        return DEFAULT_REQUEST_HEADERS.get(HeaderParameterName.REQUEST_ID.getHeaderName());
    }

    protected String getDefaultConsumerNameHeader() {
        return DEFAULT_REQUEST_HEADERS.get(HeaderParameterName.CONSUMER_NAME.getHeaderName());
    }

    protected String getDefaultLocaleHeader() {
        return DEFAULT_REQUEST_HEADERS.get(HeaderParameterName.LOCALE.getHeaderName());
    }
}
