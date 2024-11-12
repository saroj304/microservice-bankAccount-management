package com.bank_management.accounts.service.impl;

import com.bank_management.accounts.constants.AccountConstants;
import com.bank_management.accounts.dto.CustomerDto;
import com.bank_management.accounts.entity.Accounts;
import com.bank_management.accounts.entity.Customer;
import com.bank_management.accounts.mapper.CustomerMapper;
import com.bank_management.accounts.repository.AccountRepository;
import com.bank_management.accounts.repository.CustomerRepository;
import com.bank_management.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toCustomer(new Customer(), customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createAccountEntity(savedCustomer));
    }

    private Accounts createAccountEntity(Customer customerEntity) {
        long accNumber = 1000000000L + new Random().nextInt(900000000);
        return Accounts.builder()
                .accountNumber(accNumber)
                .accountType(AccountConstants.SAVINGS)
                .customerId(customerEntity.getCustomerId())
                .branchAddress(AccountConstants.ADDRESS)
                .build();
    }
}
