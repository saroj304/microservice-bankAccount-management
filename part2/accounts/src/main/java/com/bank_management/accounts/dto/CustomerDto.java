package com.bank_management.accounts.dto;

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
public class CustomerDto {
    @NotEmpty(message = "name cannot be null or empty!!")
    @Size(min = 3,max = 15 ,message = "length of name should be between 3 and 15")
    private String name;
    @NotEmpty(message = "email cannot be null or empty!!")
    private String email;
    @NotEmpty(message = "mobile number cannot be null or empty!!")
    private String mobileNumber;
    private AccountsDto accountsDto;
}
