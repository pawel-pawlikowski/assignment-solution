package com.rea.account.infrastructure.service;

import com.rea.account.infrastructure.ExchangeRates;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyExchangeRatesService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${exchangerates.url.usd}")
    private String usdExchangeRatesUrl;

    public ExchangeRates getCurrentExchangeRate() {
        return restTemplate.getForObject(usdExchangeRatesUrl, ExchangeRates.class);
    }
}
