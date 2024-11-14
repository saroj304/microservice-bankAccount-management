package com.bank_management.accounts.service;

import com.bank_management.accounts.dto.CustomerDto;
import com.bank_management.accounts.entity.Customer;

public interface IAccountService {
    /**
     * creates new account
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccountDetails(String customerId);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
