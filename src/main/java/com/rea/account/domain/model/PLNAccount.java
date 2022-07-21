package com.rea.account.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("PL")
@NoArgsConstructor
@SuperBuilder
public class PLNAccount extends CurrencyAccount {
    {
        setCurrency(Currency.PLN);
    }
}
