package com.bank_management.accounts.mapper;

import com.bank_management.accounts.dto.AccountsDto;
import com.bank_management.accounts.entity.Accounts;

public class AccountMapper {


    public Accounts toAccountEntity(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }

    public AccountsDto toAccountDto(Accounts accounts, AccountsDto accountsDto) {

        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }
}
