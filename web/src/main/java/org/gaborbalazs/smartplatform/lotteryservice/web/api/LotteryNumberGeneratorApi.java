package org.gaborbalazs.smartplatform.lotteryservice.web.api;

import io.swagger.annotations.*;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.LotteryTypeGeneratorTypeRequest;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.QuantityPoolSizeGeneratorTypeRequest;
import org.gaborbalazs.smartplatform.lotteryservice.web.validator.ValidLotteryTypeGeneratorTypeRequest;
import org.gaborbalazs.smartplatform.lotteryservice.web.validator.ValidQuantityPoolSizeGeneratorTypeRequest;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

@Validated
@RequestMapping("/lottery")
@Api(tags = {"Lottery Number Generator"})
@ApiModel(value = "Lottery Number Generator", description = "Endpoints for generating lottery number")
public interface LotteryNumberGeneratorApi {

    @RequestMapping(value = "/{lotteryType}/numbers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("Generate a set of lottery numbers based on lottery type")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Consumer-Name", value = "Name of the consumer", paramType = "header", defaultValue = "Swagger"),
            @ApiImplicitParam(name = "Request-Id", value = "Request ID", paramType = "header", defaultValue = "swagger0-0000-0000-0000-swagger00000"),
            @ApiImplicitParam(name = "Locale", value = "Locale for response message localization. Changes only numbers, dates, currency, etc.", paramType = "header", defaultValue = "en-US"),
            @ApiImplicitParam(name = "lotteryType", value = "Type of lottery", paramType = "path", defaultValue = "five-out-of-ninety", allowableValues = "five-out-of-ninety,six-out-of-forty-five,scandinavian,joker", required = true),
            @ApiImplicitParam(name = "generatorType", value = "Type of generator", paramType = "query", defaultValue = "default", allowableValues = "default,experimental")})
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Not Implemented")})
    GeneratedNumbers generate(@ApiIgnore @ValidLotteryTypeGeneratorTypeRequest LotteryTypeGeneratorTypeRequest request) throws UnsupportedOperationException;

    @RequestMapping(value = "/numbers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("Generate a set of lottery numbers based on quantity and pool size")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Consumer-Name", value = "Name of the consumer", paramType = "header", defaultValue = "Swagger"),
            @ApiImplicitParam(name = "Request-Id", value = "Request ID", paramType = "header", defaultValue = "swagger0-0000-0000-0000-swagger00000"),
            @ApiImplicitParam(name = "Locale", value = "Locale for response message localization. Changes only numbers, dates, currency, etc.", paramType = "header", defaultValue = "en-US"),
            @ApiImplicitParam(name = "quantity", value = "Quantity of drawn numbers", paramType = "query", required = true),
            @ApiImplicitParam(name = "poolSize", value = "Pool size of numbers", paramType = "query", required = true),
            @ApiImplicitParam(name = "generatorType", value = "Type of generator", paramType = "query", defaultValue = "default", allowableValues = "default,experimental")})
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Not Implemented")})
    GeneratedNumbers generate(
            @ApiIgnore @ValidQuantityPoolSizeGeneratorTypeRequest QuantityPoolSizeGeneratorTypeRequest quantityPoolSizeGeneratorTypeRequest) throws IllegalArgumentException, UnsupportedOperationException;
}
