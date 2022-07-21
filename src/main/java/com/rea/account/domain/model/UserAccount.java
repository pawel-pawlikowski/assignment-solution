package com.rea.account.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
public class UserAccount extends UserDetails{
    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.PERSIST)
    private List<CurrencyAccount> currencyAccounts = new ArrayList<>();
}
