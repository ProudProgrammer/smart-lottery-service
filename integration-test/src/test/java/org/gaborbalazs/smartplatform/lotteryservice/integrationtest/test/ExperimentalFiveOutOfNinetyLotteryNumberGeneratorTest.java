package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.test;

import com.jayway.jsonpath.DocumentContext;
import org.gaborbalazs.smartplatform.lotteryservice.integrationtest.base.LotteryNumberGeneratorTestBase;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExperimentalFiveOutOfNinetyLotteryNumberGeneratorTest extends LotteryNumberGeneratorTestBase {

    @RepeatedTest(50)
    void testWithLotteryTypeOfFiveOutOfNinety() throws Exception {
        // GIVEN
        LotteryType expectedLotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType expectedGeneratorType = GeneratorType.EXPERIMENTAL;
        int expectedResponseListSize = 5;
        List<Integer> expectedEvenNumbers = List.of(2, 3);
        List<Integer> expectedUsedPartitions = List.of(3, 4);

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(expectedLotteryType, expectedGeneratorType));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        String lotteryTypeResponse = documentContext.read("$.lotteryType", String.class);
        GeneratorType generatorTypeResponse = GeneratorType.valueOf(documentContext.read("$.generatorType", String.class));
        List<?> drawnNumbersResponse = documentContext.read("$.drawnNumbers", List.class);

        // THEN
        List<Integer> castedResponse = castToListOfIntegers(drawnNumbersResponse);
        Integer evenNumbers = countEvenNumbers(castedResponse);
        Integer usedPartitions = countUsedPartitions(castedResponse);

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedLotteryType.name(), lotteryTypeResponse);
        assertEquals(expectedGeneratorType, generatorTypeResponse);
        assertEquals(expectedResponseListSize, drawnNumbersResponse.size());
        assertTrue(expectedEvenNumbers.stream().anyMatch(evenNumbers::equals));
        assertTrue(expectedUsedPartitions.stream().anyMatch(usedPartitions::equals));
    }

    @RepeatedTest(50)
    void testWithQuantityAndPoolSize() throws Exception {
        // GIVEN
        GeneratorType expectedGeneratorType = GeneratorType.EXPERIMENTAL;
        int quantity = 5;
        int poolSize = 90;
        String expectedLotteryType = quantity + "/" + poolSize;
        List<Integer> expectedEvenNumbers = List.of(2, 3);
        List<Integer> expectedUsedPartitions = List.of(3, 4);

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(quantity, poolSize, expectedGeneratorType));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        String lotteryTypeResponse = documentContext.read("$.lotteryType", String.class);
        GeneratorType generatorTypeResponse = GeneratorType.valueOf(documentContext.read("$.generatorType", String.class));
        List<?> drawnNumbersResponse = documentContext.read("$.drawnNumbers", List.class);

        // THEN
        List<Integer> castedResponse = castToListOfIntegers(drawnNumbersResponse);
        Integer evenNumbers = countEvenNumbers(castedResponse);
        Integer usedPartitions = countUsedPartitions(castedResponse);

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedLotteryType, lotteryTypeResponse);
        assertEquals(expectedGeneratorType, generatorTypeResponse);
        assertEquals(quantity, drawnNumbersResponse.size());
        assertTrue(expectedEvenNumbers.stream().anyMatch(evenNumbers::equals));
        assertTrue(expectedUsedPartitions.stream().anyMatch(usedPartitions::equals));
    }
}
