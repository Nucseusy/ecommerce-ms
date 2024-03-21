package br.com.yesv.ecommercems.adapters.in.controller.dto;

import br.com.yesv.ecommercems.adapters.in.controller.common.serialization.LocalDateTimeDeserializer;
import br.com.yesv.ecommercems.adapters.in.controller.common.serialization.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response model that contains pricing information")
public class PriceResponse {
    @Schema(description = "Unique code or identifier assigned to a specific product.", example = "1")
    private Integer productId;

    @Schema(description = "Identifier used to denote a brand to which a product.", example = "1")
    private Integer brandId;

    @Schema(description = "Date on which the indicated rate price applies.  Format yyyy-MM-dd HH:mm:ss", example = "2024-01-01 00:00:01")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startApplicationDate;

    @Schema(description = "Date on which the indicated rate price ends. Format yyyy-MM-dd HH:mm:ss", example = "2024-01-01 11:22:33")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endApplicationDate;

    @Schema(description = "Identifier of the applicable price rate.", example = "1")
    private Integer priceList;

    @Schema(description = "Final sale price.", example = "22.34")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "#0.00")
    private Double finalPrice;
}
