package com.rea.shared.validation.validator;

import com.rea.shared.validation.annotation.ValidAge;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static java.util.Objects.nonNull;

public class AgeValidator implements ConstraintValidator<ValidAge, String> {
    @Override
    public void initialize(ValidAge constraintAnnotation) { }

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext context) {
        boolean isNumber;
        if(nonNull(pesel) && pesel.length() == 11) {
            try {
                Long.parseLong(pesel);
                isNumber = true;
            } catch(NumberFormatException ex) {
                isNumber = false;
            }
        } else {
            return false;
        }

        return isNumber && ageValid(pesel);
    }

    // check based on year of birth only (months not taken into account for simplicity reasons)
    private boolean ageValid(String pesel) {
        boolean valid = false;
        String yearOfBirthString = pesel.substring(0,2);
        String monthOfBirthString = pesel.substring(2, 4);

        int yearOfBirth = Integer.parseInt(yearOfBirthString);
        int monthOfBirth = Integer.parseInt(monthOfBirthString);
        int currentYear = LocalDate.now().getYear();


        if(monthOfBirth < 21) {
            valid = true;
        } else {
            if((yearOfBirth + 2000) <= (currentYear - 18)) {
                valid = true;
            } else {
                valid = false;
            }
        }
        return valid;
    }
}
