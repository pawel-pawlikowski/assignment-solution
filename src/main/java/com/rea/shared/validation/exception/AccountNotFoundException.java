package com.rea.shared.validation.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("Account doesn't exist");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

}
