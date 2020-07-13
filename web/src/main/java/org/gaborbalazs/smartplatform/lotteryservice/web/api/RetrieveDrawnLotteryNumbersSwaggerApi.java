package org.gaborbalazs.smartplatform.lotteryservice.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.Draw;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;

import java.util.List;

@Api(tags = {"Retrieve Drawn Lottery Numbers"})
@ApiModel(value = "Retrieve Drawn Lottery Numbers", description = "Endpoints for retrieving drawn lottery numbers")
public interface RetrieveDrawnLotteryNumbersSwaggerApi {

    @ApiOperation("Retrieving drawn lottery numbers from Szerencsejatek Zrt. based on lottery type")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Consumer-Name", value = "Name of the consumer", paramType = "header", defaultValue = "Swagger"),
            @ApiImplicitParam(name = "Request-Id", value = "Request ID", paramType = "header", defaultValue = "swagger0-0000-0000-0000-swagger00000"),
            @ApiImplicitParam(name = "Locale", value = "Locale for response message localization.", paramType = "header", defaultValue = "en-US")})
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request")})
    List<? extends Draw> retrieve(
            @ApiParam(value = "Lottery type", required = true, allowableValues = "five-out-of-ninety,six-out-of-forty-five,scandinavian,joker") LotteryType lotteryType);
}
