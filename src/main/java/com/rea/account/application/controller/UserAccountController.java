package com.rea.account.application.controller;

import com.rea.account.application.model.AccountDetailsDTO;
import com.rea.account.application.model.FundsTransferDTO;
import com.rea.account.application.model.UserAccountDTO;
import com.rea.account.domain.model.UserAccount;
import com.rea.account.domain.repository.UserAccountRepository;
import com.rea.account.domain.service.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class UserAccountController {
    private UserAccountService userAccountService;

    @PutMapping
    public ResponseEntity<URI> createUserAccount(
            @Valid @RequestBody UserAccountDTO userAccountDTO,
            UriComponentsBuilder uriComponentsBuilder) {
        String pesel = userAccountService.create(userAccountDTO);
        URI location = createNewResourcePath(uriComponentsBuilder, pesel);
        return ResponseEntity.created(location).body(location);
    }

    @GetMapping
    public ResponseEntity<AccountDetailsDTO> getUserAccount(@RequestParam String pesel) {
        return ResponseEntity.ok(userAccountService.getAccountDetails(pesel));
    }

    @PostMapping("/transfer")
    public void transferFunds(@Valid @RequestBody FundsTransferDTO fundsTransferDTO) {
        userAccountService.tranferFunds(fundsTransferDTO);
    }

    private URI createNewResourcePath(UriComponentsBuilder uriComponentsBuilder, String pesel) {
        return uriComponentsBuilder
                .path("api/v1/account/")
                .path(pesel)
                .build()
                .toUri();
    }

}
