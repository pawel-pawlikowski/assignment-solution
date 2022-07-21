package com.rea.account.infrastructure.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Rate {
    private String no;
    private LocalDate effectiveDate;
    private BigDecimal bid;
    private BigDecimal ask;
}
