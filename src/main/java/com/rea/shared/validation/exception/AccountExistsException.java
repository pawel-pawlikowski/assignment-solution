package com.rea.shared.validation.exception;

public class AccountExistsException extends RuntimeException {
    public AccountExistsException() {
        super("Account for a user with the given PESEL number already exists");
    }

    public AccountExistsException(String message) {
        super(message);
    }
}
