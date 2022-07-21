package com.rea.account.domain.service;

import com.rea.account.application.model.AccountDetailsDTO;
import com.rea.account.application.model.FundsTransferDTO;
import com.rea.account.application.model.UserAccountDTO;

public interface UserAccountService {
    String create(UserAccountDTO userAccountDTO);
    AccountDetailsDTO getAccountDetails(String pesel);
    void tranferFunds(FundsTransferDTO fundsTransferDTO);
}
