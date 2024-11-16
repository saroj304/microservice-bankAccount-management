package com.bank_management.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(
        name = "Response",
        description ="schema to hold the successful response information"
)
public class ResponseDto {
    @Schema(
            description = "status code in the response",example = "200"
    )
    private String statusCode;
    @Schema(
            description = "message in the response",example = "Account created successfully"
    )
    private String message;
}
