package com.bank_management.accounts.service;

import com.bank_management.accounts.dto.CustomerDto;
import com.bank_management.accounts.entity.Customer;

public interface ICustomerService {
   CustomerDto fetchCustomerDetials(String mobileNumber);
}
