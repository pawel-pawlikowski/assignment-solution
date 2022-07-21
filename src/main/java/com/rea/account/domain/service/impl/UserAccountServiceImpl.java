package com.rea.account.domain.service.impl;

import com.rea.account.application.model.AccountDetailsDTO;
import com.rea.account.application.model.FundsTransferDTO;
import com.rea.account.application.model.UserAccountDTO;
import com.rea.account.domain.model.CurrencyAccount;
import com.rea.account.domain.model.PLNAccount;
import com.rea.account.domain.model.USDAccount;
import com.rea.account.domain.model.UserAccount;
import com.rea.account.domain.repository.UserAccountRepository;
import com.rea.account.domain.service.UserAccountService;
import com.rea.account.infrastructure.ExchangeRates;
import com.rea.account.infrastructure.service.CurrencyExchangeRatesService;
import com.rea.shared.validation.exception.AccountExistsException;
import com.rea.shared.validation.exception.AccountNotFoundException;
import com.rea.shared.validation.exception.InsufficientFundsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountRepository userAccountRepository;
    private CurrencyExchangeRatesService currencyExchangeRatesService;

    @Override
    public String create(UserAccountDTO userAccountDTO) {
        if(!isNull(userAccountRepository.findByPesel(userAccountDTO.getPesel())))
            throw new AccountExistsException();

        return userAccountRepository.save(fromDto(userAccountDTO)).getPesel();
    }

    @Override
    public AccountDetailsDTO getAccountDetails(String pesel) {
        UserAccount userAccount = userAccountRepository.findByPesel(pesel);
        if(isNull(userAccount))
            throw new AccountNotFoundException();
        return fromEntity(userAccount);
    }

    @Override
    public void tranferFunds(FundsTransferDTO fundsTransferDTO) {
        UserAccount userAccount = userAccountRepository.findByPesel(fundsTransferDTO.getPesel());
        if(isNull(userAccount))
            throw new AccountNotFoundException();
        ExchangeRates currentExchangeRate = currencyExchangeRatesService.getCurrentExchangeRate();
        if(!fundsAvailable(userAccount, fundsTransferDTO))
            throw new InsufficientFundsException();
        exchange(userAccount, fundsTransferDTO, currentExchangeRate);
    }

    private UserAccount fromDto(UserAccountDTO userAccountDTO) {
        UserAccount account = UserAccount.builder()
                .firstName(userAccountDTO.getFirstName())
                .lastName(userAccountDTO.getLastName())
                .pesel(userAccountDTO.getPesel())
                .build();
        List<CurrencyAccount> currencyAccounts = Arrays.asList(
                    PLNAccount.builder()
                            .balance(userAccountDTO.getInitialBalance())
                            .userAccount(account)
                            .build(),
                    USDAccount.builder()
                            .balance(BigDecimal.ZERO)
                            .userAccount(account)
                            .build()
                );
        account.setCurrencyAccounts(currencyAccounts);
        return account;
    }

    private AccountDetailsDTO fromEntity(UserAccount userAccount) {
        AccountDetailsDTO accountDetailsDTO = AccountDetailsDTO.builder()
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .pesel(userAccount.getPesel())
                .build();
        userAccount.getCurrencyAccounts()
                .stream()
                .forEach(ca -> {
                    switch (ca.getCurrency()) {
                        case PLN -> accountDetailsDTO.setSubaccountPLN(ca.getBalance());
                        case USD -> accountDetailsDTO.setSubaccountUSD(ca.getBalance());
                    }

                });
        return accountDetailsDTO;
    }

    private boolean fundsAvailable(UserAccount userAccount, FundsTransferDTO fundsTransferDTO) throws AccountNotFoundException{
        return userAccount.getCurrencyAccounts()
                .stream()
                .filter(ca -> ca.getCurrency().equals(fundsTransferDTO.getFrom()))
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException())
                .getBalance().compareTo(fundsTransferDTO.getAmount()) != -1;
    }

    private void exchange(
            UserAccount userAccount,
            FundsTransferDTO fundsTransferDTO,
            ExchangeRates currentExchangeRate) {

        CurrencyAccount from =   userAccount.getCurrencyAccounts()
                .stream()
                .filter(ca -> ca.getCurrency().equals(fundsTransferDTO.getFrom()))
                .findFirst()
                .get();

        CurrencyAccount to =   userAccount.getCurrencyAccounts()
                .stream()
                .filter(ca -> ca.getCurrency().equals(fundsTransferDTO.getTo()))
                .findFirst()
                .get();

        BigDecimal deductedBalance;
        BigDecimal addedBalance;
        BigDecimal exchangedValue;
        switch (from.getCurrency()) {
            case PLN -> {
                deductedBalance = from.getBalance()
                                    .subtract(fundsTransferDTO.getAmount());
                exchangedValue =  BigDecimal.ONE
                                    .divide(currentExchangeRate.getRates().get(0).getAsk(), 2, RoundingMode.HALF_EVEN)
                                    .multiply(fundsTransferDTO.getAmount());
                addedBalance = to.getBalance()
                                .add(exchangedValue);
                from.setBalance(deductedBalance);
                to.setBalance(addedBalance);
            }
            case USD -> {
                deductedBalance = from.getBalance()
                                    .subtract(fundsTransferDTO.getAmount());
                exchangedValue =  fundsTransferDTO
                                    .getAmount()
                                    .multiply(currentExchangeRate.getRates().get(0).getAsk());
                addedBalance = to.getBalance()
                                    .add(exchangedValue);
                from.setBalance(deductedBalance);
                to.setBalance(addedBalance);
            }
        }
    }

}
