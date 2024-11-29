package com.bank_management.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Customer",
        description = "holds the customer and its account information"
)
public class CustomerDto {
    @NotEmpty(message = "name cannot be null or empty!!")
    @Size(min = 3, max = 15, message = "length of name should be between 3 and 15")
    @Schema(
            description = "name of the customer", example = "saroj khatiwada"
    )
    private String name;
    @NotEmpty(message = "email cannot be null or empty!!")
    @Schema(
            description = "email of the customer", example = "sarojkhatiwada1999@gmail.com"
    )
    private String email;
    @NotEmpty(message = "mobile number cannot be null or empty!!")
    @Schema(
            description = "mobile number of the customer", example = "9863628276"
    )
    private String mobileNumber;
    @Valid
    @Schema(
            description = "holds the account info of the customer"
    )
    private AccountsDto accountsDto;
}
