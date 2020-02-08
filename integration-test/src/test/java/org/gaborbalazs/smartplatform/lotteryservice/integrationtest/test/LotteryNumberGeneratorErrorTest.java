package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.test;

import com.jayway.jsonpath.DocumentContext;
import org.apache.commons.lang3.StringUtils;
import org.gaborbalazs.smartplatform.lotteryservice.integrationtest.base.LotteryNumberGeneratorTestBase;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LotteryNumberGeneratorErrorTest extends LotteryNumberGeneratorTestBase {

    @Test
    void testShouldRespond501WhenLotteryTypeAndGeneratorTypeTogetherUnsupported() throws Exception {
        // GIVEN
        int quantity = 6;
        int poolSize = 59;
        HttpStatus expectedHttpStatus = HttpStatus.NOT_IMPLEMENTED;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(quantity, poolSize, GeneratorType.EXPERIMENTAL));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        String responseTime = documentContext.read("$.timestamp", String.class);
        String responseStatus = documentContext.read("$.status", String.class);
        String responseError = documentContext.read("$.error", String.class);
        String responseMessage = documentContext.read("$.message", String.class);
        String responsePath = documentContext.read("$.path", String.class);

        // THEN
        assertEquals(expectedHttpStatus.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.EXPERIMENTAL.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertTrue(ZonedDateTime.now().isAfter(ZonedDateTime.parse(responseTime)));
        assertEquals(Integer.toString(expectedHttpStatus.value()), responseStatus);
        assertEquals(expectedHttpStatus.getReasonPhrase(), responseError);
        assertTrue(StringUtils.isNotBlank(responseMessage));
        assertEquals("/lottery/numbers", responsePath);
    }

    @Test
    void testShouldRespond400WhenPoolSizeLargerThan1000() throws Exception {
        // GIVEN
        int quantity = 6;
        int poolSize = 1001;
        HttpStatus expectedHttpStatus = HttpStatus.BAD_REQUEST;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(quantity, poolSize));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        String responseTime = documentContext.read("$.timestamp", String.class);
        String responseStatus = documentContext.read("$.status", String.class);
        String responseError = documentContext.read("$.error", String.class);
        String responseMessage = documentContext.read("$.message", String.class);
        String responsePath = documentContext.read("$.path", String.class);

        // THEN
        assertEquals(expectedHttpStatus.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.DEFAULT.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertTrue(ZonedDateTime.now().isAfter(ZonedDateTime.parse(responseTime)));
        assertEquals(Integer.toString(expectedHttpStatus.value()), responseStatus);
        assertEquals(expectedHttpStatus.getReasonPhrase(), responseError);
        assertTrue(StringUtils.isNotBlank(responseMessage));
        assertEquals("/lottery/numbers", responsePath);
    }

    @Test
    void testShouldRespond400WhenQuantity0() throws Exception {
        // GIVEN
        int quantity = 0;
        int poolSize = 90;
        HttpStatus expectedHttpStatus = HttpStatus.BAD_REQUEST;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(quantity, poolSize));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        String responseTime = documentContext.read("$.timestamp", String.class);
        String responseStatus = documentContext.read("$.status", String.class);
        String responseError = documentContext.read("$.error", String.class);
        String responseMessage = documentContext.read("$.message", String.class);
        String responsePath = documentContext.read("$.path", String.class);

        // THEN
        assertEquals(expectedHttpStatus.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.DEFAULT.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertTrue(ZonedDateTime.now().isAfter(ZonedDateTime.parse(responseTime)));
        assertEquals(Integer.toString(expectedHttpStatus.value()), responseStatus);
        assertEquals(expectedHttpStatus.getReasonPhrase(), responseError);
        assertTrue(StringUtils.isNotBlank(responseMessage));
        assertEquals("/lottery/numbers", responsePath);
    }

    @Test
    void testShouldRespond400WhenQuantityLargerOrEqualsThanPoolSize() throws Exception {
        // GIVEN
        int quantity = 90;
        int poolSize = 90;
        HttpStatus expectedHttpStatus = HttpStatus.BAD_REQUEST;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getLotteryNumberGeneratorUrl(quantity, poolSize));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        String responseTime = documentContext.read("$.timestamp", String.class);
        String responseStatus = documentContext.read("$.status", String.class);
        String responseError = documentContext.read("$.error", String.class);
        String responseMessage = documentContext.read("$.message", String.class);
        String responsePath = documentContext.read("$.path", String.class);

        // THEN
        assertEquals(expectedHttpStatus.value(), mvcResult.getResponse().getStatus());
        assertEquals(GeneratorType.DEFAULT.getValue(), mvcResult.getResponse().getHeader(GENERATOR_TYPE));
        assertTrue(ZonedDateTime.now().isAfter(ZonedDateTime.parse(responseTime)));
        assertEquals(Integer.toString(expectedHttpStatus.value()), responseStatus);
        assertEquals(expectedHttpStatus.getReasonPhrase(), responseError);
        assertTrue(StringUtils.isNotBlank(responseMessage));
        assertEquals("/lottery/numbers", responsePath);
    }
}
