package com.bank_management.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "holds the customer account information"
)
public class AccountsDto {
    @NotNull(message = "Account number cannot be null")
    @Min(value = 1000000000, message = "Account number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Account number must be at most 10 digits")
    @Schema(description = "Account number of the customer")
    private long accountNumber;
    @NotEmpty(message = "branchAddress cannot be null or empty!!")
    @Schema(
            description = "branchAddress of the customer!!"
    )
    private String branchAddress;
    @Schema(
            description = "accountType of the customer!!"
    )
    @NotEmpty(message = "accountType cannot be null or empty!!")
    private String accountType;
}
