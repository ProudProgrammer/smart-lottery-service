package org.gaborbalazs.smartplatform.lotteryservice.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;

import java.util.SortedSet;

@Api(tags = {"Lottery Number Generator"})
@ApiModel(value = "Lottery Number Generator", description = "Endpoints for generating lottery number")
public interface LotteryNumberGeneratorSwaggerApi {

    @ApiOperation("Generate a set of lottery numbers based on lottery type")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Consumer-Name", value = "Name of the consumer", paramType = "header", defaultValue = "Swagger"),
            @ApiImplicitParam(name = "Request-Id", value = "Request ID", paramType = "header", defaultValue = "swagger0-0000-0000-0000-swagger00000"),
            @ApiImplicitParam(name = "Locale", value = "Locale for response message localization. Changes only numbers, dates, currency, etc.", paramType = "header", defaultValue = "en-US")})
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Not Implemented")})
    SortedSet<Integer> generate(
            @ApiParam(value = "Lottery type", required = true, allowableValues = "five-out-of-ninety,six-out-of-forty-five,scandinavian") LotteryType lotteryType,
            @ApiParam(value = "Generator type", allowableValues = "default,experimental") GeneratorType generatorType) throws UnsupportedOperationException;

    @ApiOperation("Generate a set of lottery numbers based on quantity and pool size")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Consumer-Name", value = "Name of the consumer", paramType = "header", defaultValue = "Swagger"),
            @ApiImplicitParam(name = "Request-Id", value = "Request ID", paramType = "header", defaultValue = "swagger0-0000-0000-0000-swagger00000"),
            @ApiImplicitParam(name = "Locale", value = "Locale for response message localization. Changes only numbers, dates, currency, etc.", paramType = "header", defaultValue = "en-US")})
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Not Implemented")})
    SortedSet<Integer> generate(
            @ApiParam(value = "Quantity of drawn numbers", required = true) int quantity,
            @ApiParam(value = "Pool size of numbers", required = true) int poolSize,
            @ApiParam(value = "Generator type", allowableValues = "default,experimental") GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException;
}