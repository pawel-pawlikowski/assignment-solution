package com.rea.shared.validation.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String message;

    public static ErrorResponse error(String message) {
        return ErrorResponse.builder()
                    .message(message)
                    .build();
    }
}
