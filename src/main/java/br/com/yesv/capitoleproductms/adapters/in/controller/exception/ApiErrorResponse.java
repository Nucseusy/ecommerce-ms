package br.com.yesv.capitoleproductms.adapters.in.controller.exception;

import br.com.yesv.capitoleproductms.adapters.in.controller.common.serialization.LocalDateTimeDeserializer;
import br.com.yesv.capitoleproductms.adapters.in.controller.common.serialization.LocalDateTimeSerializer;
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
@Schema(description = "Response model that contains error message")
public class ApiErrorResponse {
    @Schema(description = "Date and time of the error", example = "2024-01-01 11:22:33")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timestamp;

    @Schema(description = "HTTP status code of the error", example = "400")
    private int status;

    @Schema(description = "Detailed error description", example = "Failed to convert value of type")
    private String error;

    @Schema(description = "Short error message", example = "BAD_REQUEST")
    private String message;

    @Schema(description = "Path of the request that generated the error", example = "/product/price")
    private String path;
}
