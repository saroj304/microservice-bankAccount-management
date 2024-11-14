package com.bank_management.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @NotEmpty(message = "account number cannot be null or empty")
    @Pattern(regexp = "^\\d{10}$")
    private long accountNumber;
    @NotEmpty(message = "branchAddress cannot be null or empty!!")
    private String branchAddress;
    @NotEmpty(message = "accountType cannot be null or empty!!")
    private String accountType;
}
