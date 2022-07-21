package com.rea.account.application.model;

import com.rea.account.domain.model.Currency;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
public class FundsTransferDTO {
    @NotNull
    private Currency from;
    @NotNull
    private Currency to;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String pesel;
}
