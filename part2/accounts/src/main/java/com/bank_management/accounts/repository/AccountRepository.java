package com.bank_management.accounts.repository;

import com.bank_management.accounts.dto.AccountsDto;
import com.bank_management.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findAccountsByCustomerId(long customerId);
}
