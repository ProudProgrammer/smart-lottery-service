package org.gaborbalazs.smartplatform.lotteryservice.web.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.LotteryTypeGeneratorTypeRequest;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.QuantityPoolSizeGeneratorTypeRequest;
import org.gaborbalazs.smartplatform.lotteryservice.web.validator.ValidLotteryTypeGeneratorTypeRequest;
import org.gaborbalazs.smartplatform.lotteryservice.web.validator.ValidQuantityPoolSizeGeneratorTypeRequest;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Validated
@RequestMapping("/lottery")
@Tag(name = "Lottery Number Generator", description = "Endpoints for generating lottery numbers")
public interface LotteryNumberGeneratorApi {

    @RequestMapping(value = "/{lotteryType}/numbers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Generate a set of lottery numbers based on lottery type")
    @Parameters({
            @Parameter(name = "Consumer-Name", description = "Name of the consumer", in = ParameterIn.HEADER, example = "Swagger"),
            @Parameter(name = "Request-Id", description = "Request ID", in = ParameterIn.HEADER, example = "swagger0-0000-0000-0000-swagger00000"),
            @Parameter(name = "Locale", description = "Locale for response message localization", in = ParameterIn.HEADER, example = "en-US"),
            @Parameter(name = "lotteryType", description = "Type of lottery", in = ParameterIn.PATH, example = "five-out-of-ninety", required = true),
            @Parameter(name = "generatorType", description = "Type of generator", in = ParameterIn.QUERY, example = "default")})
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "501", description = "Not Implemented")})
    GeneratedNumbers generate(@Parameter(hidden = true) @ValidLotteryTypeGeneratorTypeRequest LotteryTypeGeneratorTypeRequest request) throws UnsupportedOperationException;

    @RequestMapping(value = "/numbers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Generate a set of lottery numbers based on quantity and pool size")
    @Parameters({
            @Parameter(name = "Consumer-Name", description = "Name of the consumer", in = ParameterIn.HEADER, example = "Swagger"),
            @Parameter(name = "Request-Id", description = "Request ID", in = ParameterIn.HEADER, example = "swagger0-0000-0000-0000-swagger00000"),
            @Parameter(name = "Locale", description = "Locale for response message localization", in = ParameterIn.HEADER, example = "en-US"),
            @Parameter(name = "quantity", description = "Quantity of drawn numbers", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "poolSize", description = "Pool size of numbers", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "generatorType", description = "Type of generator", in = ParameterIn.QUERY, example = "default")})
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "501", description = "Not Implemented")})
    GeneratedNumbers generate(
            @Parameter(hidden = true) @ValidQuantityPoolSizeGeneratorTypeRequest QuantityPoolSizeGeneratorTypeRequest quantityPoolSizeGeneratorTypeRequest) throws IllegalArgumentException, UnsupportedOperationException;
}
