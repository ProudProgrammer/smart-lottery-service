package org.gaborbalazs.smartplatform.lotteryservice.web.api;

import io.swagger.annotations.*;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.Draw;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/retrieve")
@Api(tags = {"Retrieve Drawn Lottery Numbers"})
@ApiModel(value = "Retrieve Drawn Lottery Numbers", description = "Endpoints for retrieving drawn lottery numbers")
public interface RetrieveDrawnLotteryNumbersApi {

    @RequestMapping(value = "/{lotteryType}/drawnNumbers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Retrieving drawn lottery numbers from Szerencsejatek Zrt. based on lottery type")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Consumer-Name", value = "Name of the consumer", paramType = "header", defaultValue = "Swagger"),
            @ApiImplicitParam(name = "Request-Id", value = "Request ID", paramType = "header", defaultValue = "swagger0-0000-0000-0000-swagger00000"),
            @ApiImplicitParam(name = "Locale", value = "Locale for response message localization.", paramType = "header", defaultValue = "en-US")})
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request")})
    List<Draw> retrieve(@PathVariable("lotteryType") @ApiParam(value = "Lottery type", required = true, allowableValues = "five-out-of-ninety,six-out-of-forty-five,scandinavian,joker") LotteryType lotteryType);
}
