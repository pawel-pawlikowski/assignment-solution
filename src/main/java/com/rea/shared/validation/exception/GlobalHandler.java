package com.rea.shared.validation.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<ConstraintErrorResponse> validationErrors = new ArrayList<>();
        for (FieldError fr : ex.getBindingResult().getFieldErrors()) {
            validationErrors.add(ConstraintErrorResponse.error(fr));
        }
        return ResponseEntity.badRequest().body(validationErrors);
    }

    @ExceptionHandler(AccountExistsException.class)
    public ResponseEntity<ErrorResponse> handleAccountExistsException(AccountExistsException ex) {
        return ResponseEntity.badRequest().body(ErrorResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex) {
        return ResponseEntity.badRequest().body(ErrorResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(InsufficientFundsException ex) {
        return ResponseEntity.badRequest().body(ErrorResponse.error(ex.getMessage()));
    }
}
