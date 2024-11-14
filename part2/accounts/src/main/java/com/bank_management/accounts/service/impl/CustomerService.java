package com.bank_management.accounts.service.impl;

import com.bank_management.accounts.dto.AccountsDto;
import com.bank_management.accounts.dto.CustomerDto;
import com.bank_management.accounts.entity.Accounts;
import com.bank_management.accounts.entity.Customer;
import com.bank_management.accounts.exception.ResourceNotFoundException;
import com.bank_management.accounts.mapper.AccountMapper;
import com.bank_management.accounts.mapper.CustomerMapper;
import com.bank_management.accounts.repository.AccountRepository;
import com.bank_management.accounts.repository.CustomerRepository;
import com.bank_management.accounts.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Override
    public CustomerDto fetchCustomerDetials(String mobileNumber) {
        Optional<Customer> existingCustomer = Optional.ofNullable(customerRepository.findCustomerByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer with the mobile Number" + mobileNumber + "doesn't exists!!")
                ));

        Optional<Accounts> existingCustomerAccount = Optional.ofNullable(accountRepository.findAccountsByCustomerId(existingCustomer.get().getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer with the mobile Number" + mobileNumber + "doesn't exists")
                ));
        var mappedCustomerDto = CustomerMapper.toCustomerDto(new CustomerDto(), existingCustomer.get());
        mappedCustomerDto.setAccountsDto(
                AccountMapper.toAccountDto(existingCustomerAccount.get(), new AccountsDto()));
        return mappedCustomerDto;
    }
}
