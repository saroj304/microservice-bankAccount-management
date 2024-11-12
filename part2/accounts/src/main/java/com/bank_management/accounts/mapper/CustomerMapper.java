package com.bank_management.accounts.mapper;

import com.bank_management.accounts.dto.CustomerDto;
import com.bank_management.accounts.entity.Customer;

public class CustomerMapper {

    public static  Customer toCustomer(Customer customer, CustomerDto customerDto) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

    public static CustomerDto toCustomerDto(CustomerDto customerDto, Customer customer) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customerDto.getEmail());
        customerDto.setMobileNumber(customerDto.getMobileNumber());
        return customerDto;
    }
}
