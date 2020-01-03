package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.test;

import java.util.List;

import org.gaborbalazs.smartplatform.lotteryservice.common.enums.LotteryType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.DocumentContext;

class RandomLotteryNumberGeneratorTest extends TestBase {

    @Test
    void testFiveOutOfNinetyShouldReturnFiveNumbersWhenCalled() throws Exception {
        // GIVEN
        int expectedResponseListSize = 5;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getRandomLotteryNumberGeneratorUrl(LotteryType.FIVE_OUT_OF_NINETY));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<Integer> response = documentContext.read("$", List.class);

        // THEN
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResponseListSize, response.size());
    }

    @Test
    void testSixOutOfFortyFiveShouldReturnSixNumbersWhenCalled() throws Exception {
        // GIVEN
        int expectedResponseListSize = 6;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getRandomLotteryNumberGeneratorUrl(LotteryType.SIX_OUT_OF_FORTY_FIVE));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<Integer> response = documentContext.read("$", List.class);

        // THEN
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResponseListSize, response.size());
    }

    @Test
    void testScandinavianShouldReturnSevenNumbersWhenCalled() throws Exception {
        // GIVEN
        int expectedResponseListSize = 7;

        // WHEN
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getRandomLotteryNumberGeneratorUrl(LotteryType.SCANDINAVIAN));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        DocumentContext documentContext = getResponseAsJsonParser(mvcResult);
        List<Integer> response = documentContext.read("$", List.class);

        // THEN
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResponseListSize, response.size());
    }

    private String getRandomLotteryNumberGeneratorUrl(LotteryType lotteryType) {
        return "/lottery/" + lotteryType.getPathVariableName() + "/numbers/random";
    }
}
