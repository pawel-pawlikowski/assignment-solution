package com.rea;

import com.rea.account.infrastructure.service.CurrencyExchangeRatesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CurrencyExchangeRatesServiceTest {
    @Autowired
    private CurrencyExchangeRatesService currencyExchangeRatesService;

    @Test
    void test_getUSDExchangeRates() {
        assertNotNull(currencyExchangeRatesService.getCurrentExchangeRate());
    }
}
