package org.gaborbalazs.smartplatform.lotteryservice.web.api;

import java.util.SortedSet;

import org.gaborbalazs.smartplatform.lotteryservice.common.enums.LotteryType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"Lottery Number Generator"})
@ApiModel(value = "Lottery Number Generator", description = "Endpoints for generating lottery number")
public interface LotteryNumberGeneratorSwaggerApi {

    @ApiOperation("Generate a set of random lottery numbers based on lottery type")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "Consumer-Name", value = "Name of the consumer", paramType = "header", defaultValue = "Swagger"),
            @ApiImplicitParam(name = "Request-Id", value = "Request ID", paramType = "header", defaultValue = "swagger0-0000-0000-0000-swagger00000")})
    @ApiResponses( {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    SortedSet<Integer> generateRandom(
            @ApiParam(value = "Lottery type", required = true, allowableValues = "five-out-of-ninety,six-out-of-forty-five,scandinavian") LotteryType lotteryType);
}