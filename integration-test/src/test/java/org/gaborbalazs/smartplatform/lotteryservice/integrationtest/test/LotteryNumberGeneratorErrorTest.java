package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.test;

import com.jayway.jsonpath.DocumentContext;
import org.apache.commons.lang3.StringUtils;
import org.gaborbalazs.smartplatform.lotteryservice.integrationtest.base.LotteryNumberGeneratorTestBase;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LotteryNumberGeneratorErrorTest extends LotteryNumberGeneratorTestBase {

    @Test
    void testShouldRespond501WhenLotteryTypeAndGeneratorTypeTogetherUnsupported() throws Exception {
        // GIVEN
        int quantity = 6;
        int poolSize = 59;
        HttpStatus expectedHttpStatus = HttpStatus.NOT_IMPLEMENTED;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(quantity, poolSize, GeneratorType.EXPERIMENTAL));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        String responseTime = documentContext.read("$.timestamp", String.class);
        String responseStatus = documentContext.read("$.status", String.class);
        String responseError = documentContext.read("$.error", String.class);
        String responseMessage = documentContext.read("$.message", String.class);
        String responsePath = documentContext.read("$.path", String.class);
        String responseQuery = documentContext.read("$.query", String.class);

        // THEN
        assertEquals(expectedHttpStatus.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getValue()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getValue()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getValue()));
        assertNotNull(ZonedDateTime.parse(responseTime));
        assertEquals(Integer.toString(expectedHttpStatus.value()), responseStatus);
        assertEquals(expectedHttpStatus.getReasonPhrase(), responseError);
        assertTrue(StringUtils.isNotBlank(responseMessage));
        assertEquals("/lottery/numbers", responsePath);
        assertEquals("quantity=" + quantity + "&poolSize=" + poolSize + "&generatorType=" + GeneratorType.EXPERIMENTAL.getValue(), responseQuery);
    }

    @Test
    void testShouldRespond400WhenPoolSizeLargerThan1000() throws Exception {
        // GIVEN
        int quantity = 6;
        int poolSize = 1001;
        HttpStatus expectedHttpStatus = HttpStatus.BAD_REQUEST;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(quantity, poolSize));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        String responseTime = documentContext.read("$.timestamp", String.class);
        String responseStatus = documentContext.read("$.status", String.class);
        String responseError = documentContext.read("$.error", String.class);
        String responseMessage = documentContext.read("$.message", String.class);
        String responsePath = documentContext.read("$.path", String.class);
        String responseQuery = documentContext.read("$.query", String.class);

        // THEN
        assertEquals(expectedHttpStatus.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getValue()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getValue()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getValue()));
        assertNotNull(ZonedDateTime.parse(responseTime));
        assertEquals(Integer.toString(expectedHttpStatus.value()), responseStatus);
        assertEquals(expectedHttpStatus.getReasonPhrase(), responseError);
        assertTrue(StringUtils.isNotBlank(responseMessage));
        assertEquals("/lottery/numbers", responsePath);
        assertEquals("quantity=" + quantity + "&poolSize=" + poolSize, responseQuery);
    }

    @Test
    void testShouldRespond400WhenQuantity0() throws Exception {
        // GIVEN
        int quantity = 0;
        int poolSize = 90;
        HttpStatus expectedHttpStatus = HttpStatus.BAD_REQUEST;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(quantity, poolSize));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        String responseTime = documentContext.read("$.timestamp", String.class);
        String responseStatus = documentContext.read("$.status", String.class);
        String responseError = documentContext.read("$.error", String.class);
        String responseMessage = documentContext.read("$.message", String.class);
        String responsePath = documentContext.read("$.path", String.class);
        String responseQuery = documentContext.read("$.query", String.class);

        // THEN
        assertEquals(expectedHttpStatus.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getValue()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getValue()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getValue()));
        assertNotNull(ZonedDateTime.parse(responseTime));
        assertEquals(Integer.toString(expectedHttpStatus.value()), responseStatus);
        assertEquals(expectedHttpStatus.getReasonPhrase(), responseError);
        assertTrue(StringUtils.isNotBlank(responseMessage));
        assertEquals("/lottery/numbers", responsePath);
        assertEquals("quantity=" + quantity + "&poolSize=" + poolSize, responseQuery);
    }

    @Test
    void testShouldRespond400WhenQuantityLargerOrEqualsThanPoolSize() throws Exception {
        // GIVEN
        int quantity = 90;
        int poolSize = 90;
        HttpStatus expectedHttpStatus = HttpStatus.BAD_REQUEST;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(quantity, poolSize));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        String responseTime = documentContext.read("$.timestamp", String.class);
        String responseStatus = documentContext.read("$.status", String.class);
        String responseError = documentContext.read("$.error", String.class);
        String responseMessage = documentContext.read("$.message", String.class);
        String responsePath = documentContext.read("$.path", String.class);
        String responseQuery = documentContext.read("$.query", String.class);

        // THEN
        assertEquals(expectedHttpStatus.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getValue()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getValue()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getValue()));
        assertNotNull(ZonedDateTime.parse(responseTime));
        assertEquals(Integer.toString(expectedHttpStatus.value()), responseStatus);
        assertEquals(expectedHttpStatus.getReasonPhrase(), responseError);
        assertTrue(StringUtils.isNotBlank(responseMessage));
        assertEquals("/lottery/numbers", responsePath);
        assertEquals("quantity=" + quantity + "&poolSize=" + poolSize, responseQuery);
    }

    @Test
    void testShouldRespond400WhenLotteryTypePathWrong() throws Exception {
        // GIVEN
        String wrongLotteryType = "wrong_lottery_type";
        HttpStatus expectedHttpStatus = HttpStatus.BAD_REQUEST;

        // WHEN
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(lotteryNumberGeneratorUrlFactory.create(wrongLotteryType));
        lotteryNumberGeneratorHeaderFactory.getDefaultRequestHeaders().forEach(requestBuilder::header);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = documentContextFactory.create(mvcResult);
        String responseTime = documentContext.read("$.timestamp", String.class);
        String responseStatus = documentContext.read("$.status", String.class);
        String responseError = documentContext.read("$.error", String.class);
        String responseMessage = documentContext.read("$.message", String.class);
        String responsePath = documentContext.read("$.path", String.class);
        String responseQuery = documentContext.read("$.query", String.class);

        // THEN
        assertEquals(expectedHttpStatus.value(), mvcResult.getResponse().getStatus());
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultRequestIdHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.REQUEST_ID.getValue()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultConsumerNameHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.CONSUMER_NAME.getValue()));
        assertEquals(lotteryNumberGeneratorHeaderFactory.getDefaultLocaleHeader(), mvcResult.getResponse().getHeader(HeaderParameterName.LOCALE.getValue()));
        assertNotNull(ZonedDateTime.parse(responseTime));
        assertEquals(Integer.toString(expectedHttpStatus.value()), responseStatus);
        assertEquals(expectedHttpStatus.getReasonPhrase(), responseError);
        assertTrue(StringUtils.isNotBlank(responseMessage));
        assertEquals("/lottery/" + wrongLotteryType + "/numbers", responsePath);
        assertNull(responseQuery);
    }
}
