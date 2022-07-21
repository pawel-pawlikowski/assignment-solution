package com.rea.account.domain.repository;

import com.rea.account.domain.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByPesel(String pesel);
}
