package com.bank_management.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accounts extends BaseEntity {
    @Id
    private long accountNumber;
    private String branchAddress;
    private String accountType;
    private long customerId;
}
