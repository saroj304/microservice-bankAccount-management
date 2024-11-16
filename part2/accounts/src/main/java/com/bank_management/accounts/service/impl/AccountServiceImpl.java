package com.bank_management.accounts.service.impl;

import com.bank_management.accounts.constants.AccountConstants;
import com.bank_management.accounts.dto.AccountsDto;
import com.bank_management.accounts.dto.CustomerDto;
import com.bank_management.accounts.entity.Accounts;
import com.bank_management.accounts.entity.Customer;
import com.bank_management.accounts.exception.CustomerAlreadyExistsException;
import com.bank_management.accounts.exception.ResourceNotFoundException;
import com.bank_management.accounts.mapper.AccountMapper;
import com.bank_management.accounts.mapper.CustomerMapper;
import com.bank_management.accounts.repository.AccountRepository;
import com.bank_management.accounts.repository.CustomerRepository;
import com.bank_management.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    /**
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toCustomer(new Customer(), customerDto);
        Optional<Customer> existingCustomer = customerRepository.findCustomerByMobileNumber(customer.getMobileNumber());
        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("customer with the mobile number " + customer.getMobileNumber() + " already exists");
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createAccountEntity(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
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

    private Accounts createAccountEntity(Customer customerEntity) {
        long accNumber = 1000000000L + new Random().nextInt(900000000);
        return Accounts.builder()
                .accountNumber(accNumber)
                .accountType(AccountConstants.SAVINGS)
                .customerId(customerEntity.getCustomerId())
                .branchAddress(AccountConstants.ADDRESS)
                .build();
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account Number " + accountsDto.getAccountNumber() + " not found")
            );
            AccountMapper.toAccountEntity(accountsDto, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer " + customerId + " not found")
            );
            CustomerMapper.toCustomer(customer, customerDto);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted=false;
        if (!mobileNumber.isBlank()) {
            Optional<Customer> customer = customerRepository.findCustomerByMobileNumber(mobileNumber);
            if (!customer.isPresent()) {
                throw new ResourceNotFoundException("Customer " + mobileNumber + " not found");
            }
            Optional<Accounts> accounts = accountRepository.findAccountsByCustomerId(customer.get().getCustomerId());
            if (!accounts.isPresent()) {
                throw new ResourceNotFoundException("Account " + mobileNumber + " not found");
            }
            customerRepository.deleteById(customer.get().getCustomerId());
            accountRepository.deleteById(accounts.get().getAccountNumber());
          return true;
        }
        return isDeleted;
    }
}
