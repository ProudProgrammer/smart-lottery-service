package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Locale;

import org.gaborbalazs.smartplatform.lotteryservice.integrationtest.base.LotteryNumberGeneratorTestBase;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.DocumentContext;

class ExperimentalFiveOutOfNinetyLotteryNumberGeneratorTest extends LotteryNumberGeneratorTestBase {

    @RepeatedTest(50)
    void testWithLotteryTypeOfFiveOutOfNinety() throws Exception {
        // GIVEN
        int expectedResponseListSize = 5;
        List<Integer> expectedEvenNumbers = List.of(2, 3);
        List<Integer> expectedUsedPartitions = List.of(3, 4);
        String expectedGeneratorTypeHeader = GeneratorType.EXPERIMENTAL.getValue();
        String expectedLocaleHeader = Locale.getDefault().toString();

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(LotteryType.FIVE_OUT_OF_NINETY, GeneratorType.EXPERIMENTAL));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<?> response = documentContext.read("$", List.class);

        // THEN
        List<Integer> castedResponse = castToListOfIntegers(response);
        Integer evenNumbers = countEvenNumbers(castedResponse);
        Integer usedPartitions = countUsedPartitions(castedResponse);

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(expectedGeneratorTypeHeader, mvcResult.getResponse().getHeader(HeaderParameterName.GENERATOR_TYPE.getHeaderName()));
        assertEquals(expectedLocaleHeader, mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedResponseListSize, response.size());
        assertTrue(expectedEvenNumbers.stream().anyMatch(evenNumbers::equals));
        assertTrue(expectedUsedPartitions.stream().anyMatch(usedPartitions::equals));
    }

    @RepeatedTest(50)
    void testWithQuantityAndPoolSize() throws Exception {
        // GIVEN
        int quantity = 5;
        int poolSize = 90;
        List<Integer> expectedEvenNumbers = List.of(2, 3);
        List<Integer> expectedUsedPartitions = List.of(3, 4);
        String expectedGeneratorTypeHeader = GeneratorType.EXPERIMENTAL.getValue();
        String expectedLocaleHeader = Locale.getDefault().toString();

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(quantity, poolSize, GeneratorType.EXPERIMENTAL));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<?> response = documentContext.read("$", List.class);

        // THEN
        List<Integer> castedResponse = castToListOfIntegers(response);
        Integer evenNumbers = countEvenNumbers(castedResponse);
        Integer usedPartitions = countUsedPartitions(castedResponse);

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(expectedGeneratorTypeHeader, mvcResult.getResponse().getHeader(HeaderParameterName.GENERATOR_TYPE.getHeaderName()));
        assertEquals(expectedLocaleHeader, mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(quantity, response.size());
        assertTrue(expectedEvenNumbers.stream().anyMatch(evenNumbers::equals));
        assertTrue(expectedUsedPartitions.stream().anyMatch(usedPartitions::equals));
    }
}
