package com.rea.shared.validation.annotation;

import com.rea.shared.validation.validator.PeselValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {PeselValidator.class})
public @interface Pesel {
    String message() default "PESEL is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
