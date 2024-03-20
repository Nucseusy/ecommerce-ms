package br.com.yesv.capitoleproductms.adapters.in.controller;

import br.com.yesv.capitoleproductms.adapters.in.controller.common.Constants;
import br.com.yesv.capitoleproductms.adapters.in.controller.dto.PriceResponse;
import br.com.yesv.capitoleproductms.adapters.in.controller.exception.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/product")
@Tag(name = "Product", description = "Operations related to products")
public class ProductController {

    @GetMapping("/price")
    @Operation(summary = "Get Price", description = "Get applicable price for a product",
            responses = {
                    @ApiResponse(description = "Successful response", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PriceResponse.class))),
                    @ApiResponse(description = "Bad request (invalid or missing parameters)", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
                    @ApiResponse(description = "Price not found for the given parameters", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            },
            parameters = {
                    @Parameter(name = "productId", description = "Unique code or identifier assigned to a specific product.", required = true, schema = @Schema(type = "integer", example = "1")),
                    @Parameter(name = "brandId", description = "Identifier used to denote a brand to which a product.", required = true, schema = @Schema(type = "integer", example = "1")),
                    @Parameter(name = "applicationDate", description = "The date on which the tariff price for the product becomes effective or applicable. Format yyyy-MM-dd HH:mm:ss", required = true, example = "2024-01-01 00:00:01")
            }
    )

    public PriceResponse getPriceByParams(@RequestParam Integer productId,
                                          @RequestParam Integer brandId,
                                          @RequestParam @DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT) LocalDateTime applicationDate) {
        return PriceResponse.builder()
                .productId(1)
                .brandId(1)
                .startApplicationDate(LocalDateTime.now())
                .endApplicationDate(LocalDateTime.now())
                .priceList(1)
                .finalPrice(22.4D)
                .build();
    }
}
