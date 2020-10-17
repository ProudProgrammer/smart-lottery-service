package org.gaborbalazs.smartplatform.lotteryservice.web.api;

import io.swagger.annotations.*;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.QuantityAndPoolSize;
import org.gaborbalazs.smartplatform.lotteryservice.web.validator.ValidQuantityAndPoolSize;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
            @ApiImplicitParam(name = "Locale", value = "Locale for response message localization. Changes only numbers, dates, currency, etc.", paramType = "header", defaultValue = "en-US")})
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Not Implemented")})
    GeneratedNumbers generate(
            @PathVariable("lotteryType") @ApiParam(value = "Lottery type", required = true, allowableValues = "five-out-of-ninety,six-out-of-forty-five,scandinavian,joker") LotteryType lotteryType,
            @RequestParam(defaultValue = "default") @ApiParam(value = "Generator type", allowableValues = "default,experimental") GeneratorType generatorType)
            throws UnsupportedOperationException;

    @RequestMapping(value = "/numbers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("Generate a set of lottery numbers based on quantity and pool size")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Consumer-Name", value = "Name of the consumer", paramType = "header", defaultValue = "Swagger"),
            @ApiImplicitParam(name = "Request-Id", value = "Request ID", paramType = "header", defaultValue = "swagger0-0000-0000-0000-swagger00000"),
            @ApiImplicitParam(name = "Locale", value = "Locale for response message localization. Changes only numbers, dates, currency, etc.", paramType = "header", defaultValue = "en-US")})
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Not Implemented")})
    GeneratedNumbers generate(
            @ValidQuantityAndPoolSize QuantityAndPoolSize quantityAndPoolSize,
            @RequestParam(defaultValue = "default") @ApiParam(value = "Generator type", allowableValues = "default,experimental") GeneratorType generatorType)
            throws IllegalArgumentException, UnsupportedOperationException;
}
