package com.bank_management.accounts.exception;

public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String message) {
        super(message);
    }
}
