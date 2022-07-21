package com.rea.account.application.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountDetailsDTO {
    private String firstName;
    private String lastName;
    private String pesel;
    private BigDecimal subaccountPLN;
    private BigDecimal subaccountUSD;
}
