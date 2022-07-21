package com.rea.account.application.model;

import com.rea.shared.validation.annotation.Pesel;
import com.rea.shared.validation.annotation.ValidAge;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
public class UserAccountDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @ValidAge
    @Pesel
    private String pesel;
    @NotNull
    private BigDecimal initialBalance;
}
