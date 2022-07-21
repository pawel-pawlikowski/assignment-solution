package com.rea.account.infrastructure;

import com.rea.account.infrastructure.model.Rate;
import lombok.Data;

import java.util.List;

@Data
public class ExchangeRates {
    private char table;
    private String currency;
    private String code;
    List<Rate> rates;
}
