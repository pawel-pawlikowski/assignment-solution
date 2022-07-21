package com.rea.shared.validation.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

@Getter
@Setter
@Builder
public class ConstraintErrorResponse {
    private String fieldName;
    private Object invalidValue;
    private String message;

    public static ConstraintErrorResponse error(FieldError fieldError) {
        return ConstraintErrorResponse.builder()
                .fieldName(fieldError.getField())
                .invalidValue(fieldError.getRejectedValue())
                .message(fieldError.getDefaultMessage())
                .build();
    }
}
