package com.rea.shared.validation.annotation;

import com.rea.shared.validation.validator.AgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {AgeValidator.class})
public @interface ValidAge {
    String message() default "Accounts are only available for adults.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
