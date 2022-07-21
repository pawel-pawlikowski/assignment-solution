package com.rea.account.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "c_code")
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class CurrencyAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

}
