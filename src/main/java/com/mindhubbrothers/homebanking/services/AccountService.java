package com.mindhubbrothers.homebanking.services;

import com.mindhubbrothers.homebanking.dto.AccountDTO;
import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AccountService {

    Account findByNumber(String number);

    Boolean existsByNumber(String number);

    void saveAccount(Account account);

    List<AccountDTO> getAccountsDTO(Client client);

    ResponseEntity<?> createAccount(Authentication authentication);

    void createAccountForClient(Client client);

    ResponseEntity<?> getAccounts(Authentication authentication); //get
}
