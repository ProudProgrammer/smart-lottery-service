package org.gaborbalazs.smartplatform.lotteryservice.web.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.Draw;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/retrieve")
@Tag(name = "Retrieve Drawn Lottery Numbers", description = "Endpoints for retrieving drawn lottery numbers")
public interface RetrieveDrawnLotteryNumbersApi {

    @RequestMapping(value = "/{lotteryType}/drawnNumbers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieving drawn lottery numbers from Szerencsejatek Zrt. based on lottery type")
    @Parameters({
            @Parameter(name = "Consumer-Name", description = "Name of the consumer", in = ParameterIn.HEADER, example = "Swagger"),
            @Parameter(name = "Request-Id", description = "Request ID", in = ParameterIn.HEADER, example = "swagger0-0000-0000-0000-swagger00000"),
            @Parameter(name = "Locale", description = "Locale for response message localization", in = ParameterIn.HEADER, example = "en-US")})
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    List<Draw> retrieve(
            @PathVariable("lotteryType")
            @Parameter(
                    description = "Lottery type",
                    required = true,
                    in = ParameterIn.PATH,
                    schema = @Schema(
                            implementation = LotteryType.class,
                            allowableValues = {"five-out-of-ninety", "six-out-of-forty-five", "scandinavian", "joker"}
                    )
            )
            LotteryType lotteryType);
}
