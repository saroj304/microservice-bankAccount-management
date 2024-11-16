package com.bank_management.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "holds the customer account information"
)
public class AccountsDto {
    @NotEmpty(message = "account number cannot be null or empty")
    @Pattern(regexp = "^\\d{10}$")
    @Schema(

            description = "accountNumber of the customer!!"
    )
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
