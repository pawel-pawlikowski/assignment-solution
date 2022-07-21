package com.rea.account.domain.model;

import com.rea.shared.validation.annotation.Pesel;
import com.rea.shared.validation.annotation.ValidAge;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ValidAge
    @Pesel
    private String pesel;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}
