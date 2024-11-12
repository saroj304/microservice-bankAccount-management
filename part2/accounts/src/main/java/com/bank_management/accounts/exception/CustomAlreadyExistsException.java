package com.bank_management.accounts.exception;

public class CustomAlreadyExistsException extends RuntimeException {
    public CustomAlreadyExistsException(String message) {
        super(message);
    }

}
