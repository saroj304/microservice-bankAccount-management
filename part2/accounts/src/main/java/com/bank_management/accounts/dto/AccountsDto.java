package com.bank_management.accounts.dto;

import lombok.Data;

@Data
public class AccountsDto {
    private long accountNumber;
    private String branchAddress;
    private String accountType;
}
