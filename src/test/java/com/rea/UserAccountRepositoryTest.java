package com.rea;

import com.rea.account.domain.model.PLNAccount;
import com.rea.account.domain.model.USDAccount;
import com.rea.account.domain.model.UserAccount;
import com.rea.account.domain.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserAccountRepositoryTest {

    @Autowired
    UserAccountRepository userAccountRepository;

    @BeforeAll
    void beforeAll() {
        userAccountRepository.save(createTestAccount("60061464719"));
    }

    @Test
    void test_findSavedUserAccount() {
       assertNotNull(userAccountRepository.findByPesel("60061464719"));
    }

    @Test
    @Transactional
    void test_findSavedCurrencyAccountsForUserAccount() {
        assertTrue(userAccountRepository.findByPesel("60061464719").getCurrencyAccounts().size() == 2);
    }

    @Test
    void test_exceptionOnInvalidPeselNumber() {
        assertThrows(ConstraintViolationException.class, () -> {
            userAccountRepository.save(createTestAccount("01234567890"));
        });
    }

    @Test
    void test_exceptionOnInvalidAge() {
        assertThrows(ConstraintViolationException.class, () -> {
            userAccountRepository.save(createTestAccount("08321512426"));
        });
    }

    private UserAccount createTestAccount(String pesel) {
        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName("John");
        userAccount.setLastName("Doe");
        userAccount.setPesel(pesel);

        PLNAccount plnAccount = new PLNAccount();
        USDAccount usdAccount = new USDAccount();
        plnAccount.setUserAccount(userAccount);
        usdAccount.setUserAccount(userAccount);

        userAccount.getCurrencyAccounts().add(plnAccount);
        userAccount.getCurrencyAccounts().add(usdAccount);
        return userAccount;
    }
}
