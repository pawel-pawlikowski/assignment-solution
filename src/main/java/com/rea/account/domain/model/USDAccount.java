package com.rea.account.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("US")
@NoArgsConstructor
@SuperBuilder
public class USDAccount extends CurrencyAccount {
    {
        setCurrency(Currency.USD);
    }
}
