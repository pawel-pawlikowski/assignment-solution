package com.rea.shared.validation.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Account balance is lower than the requested amount");
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
