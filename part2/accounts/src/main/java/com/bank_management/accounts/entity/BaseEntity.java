package com.bank_management.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @Column(updatable = false)
    private String createdBy;
    @Column(insertable = false)
    private LocalDateTime updatedAt;
    @Column(insertable = false)
    private String updatedBy;
}
