package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.gaborbalazs.smartplatform.lotteryservice.integrationtest.base.LotteryNumberGeneratorTestBase;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.DocumentContext;

class DefaultLotteryNumberGeneratorTest extends LotteryNumberGeneratorTestBase {

    @Test
    void testWithLotteryTypeOfFiveOutOfNinetyWithoutGeneratorType() throws Exception {
        // GIVEN
        int expectedResponseListSize = 5;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(LotteryType.FIVE_OUT_OF_NINETY));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<Integer> response = documentContext.read("$", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.DEFAULT.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertEquals(expectedResponseListSize, response.size());
    }

    @Test
    void testWithLotteryTypeOfFiveOutOfNinetyWithGeneratorType() throws Exception {
        // GIVEN
        int expectedResponseListSize = 5;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(LotteryType.FIVE_OUT_OF_NINETY, GeneratorType.DEFAULT));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<Integer> response = documentContext.read("$", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.DEFAULT.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertEquals(expectedResponseListSize, response.size());
    }

    @Test
    void testWithLotteryTypeOfSixOutOfFortyFiveWithoutGeneratorType() throws Exception {
        // GIVEN
        int expectedResponseListSize = 6;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(LotteryType.SIX_OUT_OF_FORTY_FIVE));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<Integer> response = documentContext.read("$", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.DEFAULT.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertEquals(expectedResponseListSize, response.size());
    }

    @Test
    void testWithLotteryTypeOfSixOutOfFortyFiveWithGeneratorType() throws Exception {
        // GIVEN
        int expectedResponseListSize = 6;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(LotteryType.SIX_OUT_OF_FORTY_FIVE, GeneratorType.DEFAULT));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<Integer> response = documentContext.read("$", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.DEFAULT.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertEquals(expectedResponseListSize, response.size());
    }

    @Test
    void testWithLotteryTypeOfScandinavianWithoutGeneratorType() throws Exception {
        // GIVEN
        int expectedResponseListSize = 7;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(LotteryType.SCANDINAVIAN));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<Integer> response = documentContext.read("$", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.DEFAULT.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertEquals(expectedResponseListSize, response.size());
    }

    @Test
    void testWithLotteryTypeOfScandinavianWithGeneratorType() throws Exception {
        // GIVEN
        int expectedResponseListSize = 7;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(LotteryType.SCANDINAVIAN, GeneratorType.DEFAULT));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<Integer> response = documentContext.read("$", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.DEFAULT.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertEquals(expectedResponseListSize, response.size());
    }

    @Test
    void testWithQuantityAndPoolSizeWithoutGeneratorType() throws Exception {
        // GIVEN
        int quantity = 6;
        int poolSize = 59;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(quantity, poolSize));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<Integer> response = documentContext.read("$", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.DEFAULT.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertEquals(quantity, response.size());
    }

    @Test
    void testWithQuantityAndPoolSizeWithGeneratorType() throws Exception {
        // GIVEN
        int quantity = 6;
        int poolSize = 59;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(quantity, poolSize, GeneratorType.DEFAULT));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<Integer> response = documentContext.read("$", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.DEFAULT.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertEquals(quantity, response.size());
    }
}
