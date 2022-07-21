package com.rea.shared.validation.validator;

import com.rea.shared.validation.annotation.Pesel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;

public class PeselValidator implements ConstraintValidator<Pesel, String> {
    @Override
    public void initialize(Pesel constraintAnnotation) { }

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


        return isNumber && numberValid(toIntArray(pesel));
    }

    private int[] toIntArray(String pesel) {
        int[] peselIntArray = new int[pesel.length()];
        for(int i = 0; i < pesel.length(); i++) {
            peselIntArray[i] = Integer.parseInt(String.valueOf(pesel.charAt(i)));
        }
        return peselIntArray;
    }

    private boolean numberValid(int[] peselDigits) {
        int controlDigit = peselDigits[10];
        int sum = 0;
        for(int i = 0; i < peselDigits.length - 1; i++) {
            if(i == 0 || i == 4 || i == 8)
                sum += peselDigits[i];
            else if( i == 1 || i == 5 || i == 9)
                sum += (3*peselDigits[i]);
            else if ( i == 2 || i == 6)
                sum += (7*peselDigits[i]);
            else
                sum += (9*peselDigits[i]);
        }

        String sumAsString = String.valueOf(sum);
        int sumLastDigit = Integer.valueOf(String.valueOf(sumAsString.charAt(sumAsString.length()-1)));
        return (10 - sumLastDigit) == controlDigit;
    }
}
