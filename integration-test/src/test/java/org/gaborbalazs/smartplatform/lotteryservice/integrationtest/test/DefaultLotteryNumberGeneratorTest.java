package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.test;

import com.jayway.jsonpath.DocumentContext;
import org.gaborbalazs.smartplatform.lotteryservice.integrationtest.base.LotteryNumberGeneratorTestBase;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultLotteryNumberGeneratorTest extends LotteryNumberGeneratorTestBase {

    @Test
    void testWithLotteryTypeOfFiveOutOfNinetyWithoutGeneratorType() throws Exception {
        // GIVEN
        GeneratorType expectedGeneratorType = GeneratorType.DEFAULT;
        int expectedResponseListSize = 5;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(LotteryType.FIVE_OUT_OF_NINETY));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        GeneratorType generatorTypeResponse = GeneratorType.valueOf(documentContext.read("$.generatorType", String.class));
        List<?> drawnNumbersResponse = documentContext.read("$.drawnNumbers", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedGeneratorType, generatorTypeResponse);
        assertEquals(expectedResponseListSize, drawnNumbersResponse.size());
    }

    @Test
    void testWithLotteryTypeOfFiveOutOfNinetyWithGeneratorType() throws Exception {
        // GIVEN
        GeneratorType expectedGeneratorType = GeneratorType.DEFAULT;
        int expectedResponseListSize = 5;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(LotteryType.FIVE_OUT_OF_NINETY, GeneratorType.DEFAULT));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        GeneratorType generatorTypeResponse = GeneratorType.valueOf(documentContext.read("$.generatorType", String.class));
        List<?> drawnNumbersResponse = documentContext.read("$.drawnNumbers", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedGeneratorType, generatorTypeResponse);
        assertEquals(expectedResponseListSize, drawnNumbersResponse.size());
    }

    @Test
    void testWithLotteryTypeOfSixOutOfFortyFiveWithoutGeneratorType() throws Exception {
        // GIVEN
        GeneratorType expectedGeneratorType = GeneratorType.DEFAULT;
        int expectedResponseListSize = 6;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(LotteryType.SIX_OUT_OF_FORTY_FIVE));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        GeneratorType generatorTypeResponse = GeneratorType.valueOf(documentContext.read("$.generatorType", String.class));
        List<?> drawnNumbersResponse = documentContext.read("$.drawnNumbers", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedGeneratorType, generatorTypeResponse);
        assertEquals(expectedResponseListSize, drawnNumbersResponse.size());
    }

    @Test
    void testWithLotteryTypeOfSixOutOfFortyFiveWithGeneratorType() throws Exception {
        // GIVEN
        GeneratorType expectedGeneratorType = GeneratorType.DEFAULT;
        int expectedResponseListSize = 6;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(lotteryNumberGeneratorUrlFactory.create(LotteryType.SIX_OUT_OF_FORTY_FIVE, GeneratorType.DEFAULT));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        GeneratorType generatorTypeResponse = GeneratorType.valueOf(documentContext.read("$.generatorType", String.class));
        List<?> drawnNumbersResponse = documentContext.read("$.drawnNumbers", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedGeneratorType, generatorTypeResponse);
        assertEquals(expectedResponseListSize, drawnNumbersResponse.size());
    }

    @Test
    void testWithLotteryTypeOfScandinavianWithoutGeneratorType() throws Exception {
        // GIVEN
        GeneratorType expectedGeneratorType = GeneratorType.DEFAULT;
        int expectedResponseListSize = 7;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(LotteryType.SCANDINAVIAN));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        GeneratorType generatorTypeResponse = GeneratorType.valueOf(documentContext.read("$.generatorType", String.class));
        List<?> drawnNumbersResponse = documentContext.read("$.drawnNumbers", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedGeneratorType, generatorTypeResponse);
        assertEquals(expectedResponseListSize, drawnNumbersResponse.size());
    }

    @Test
    void testWithLotteryTypeOfScandinavianWithGeneratorType() throws Exception {
        // GIVEN
        GeneratorType expectedGeneratorType = GeneratorType.DEFAULT;
        int expectedResponseListSize = 7;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(LotteryType.SCANDINAVIAN, GeneratorType.DEFAULT));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        GeneratorType generatorTypeResponse = GeneratorType.valueOf(documentContext.read("$.generatorType", String.class));
        List<?> drawnNumbersResponse = documentContext.read("$.drawnNumbers", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedGeneratorType, generatorTypeResponse);
        assertEquals(expectedResponseListSize, drawnNumbersResponse.size());
    }

    @Test
    void testWithQuantityAndPoolSizeWithoutGeneratorType() throws Exception {
        // GIVEN
        GeneratorType expectedGeneratorType = GeneratorType.DEFAULT;
        int quantity = 6;
        int poolSize = 59;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(quantity, poolSize));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        GeneratorType generatorTypeResponse = GeneratorType.valueOf(documentContext.read("$.generatorType", String.class));
        List<?> drawnNumbersResponse = documentContext.read("$.drawnNumbers", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedGeneratorType, generatorTypeResponse);
        assertEquals(quantity, drawnNumbersResponse.size());
    }

    @Test
    void testWithQuantityAndPoolSizeWithGeneratorType() throws Exception {
        // GIVEN
        GeneratorType expectedGeneratorType = GeneratorType.DEFAULT;
        int quantity = 6;
        int poolSize = 59;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(quantity, poolSize, GeneratorType.DEFAULT));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        GeneratorType generatorTypeResponse = GeneratorType.valueOf(documentContext.read("$.generatorType", String.class));
        List<?> drawnNumbersResponse = documentContext.read("$.drawnNumbers", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedGeneratorType, generatorTypeResponse);
        assertEquals(quantity, drawnNumbersResponse.size());
    }

    @Test
    void testResponseShouldContainProperlyGeneratedHeadersWhenRequestNoHeaders() throws Exception {
        // GIVEN
        GeneratorType expectedGeneratorType = GeneratorType.DEFAULT;
        int quantity = 6;
        int poolSize = 59;
        String expectedLocaleHeader = Locale.getDefault().toString();
        String expectedConsumerName = "unidentified";

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(quantity, poolSize, GeneratorType.DEFAULT));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        GeneratorType generatorTypeResponse = GeneratorType.valueOf(documentContext.read("$.generatorType", String.class));
        List<?> drawnNumbersResponse = documentContext.read("$.drawnNumbers", List.class);

        // THEN
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertNotNull(mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getHeaderName()));
        assertEquals(expectedConsumerName, mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName()));
        assertEquals(expectedLocaleHeader, mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        assertEquals(expectedGeneratorType, generatorTypeResponse);
        assertEquals(quantity, drawnNumbersResponse.size());
    }
}
