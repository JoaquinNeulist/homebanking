package com.mindhubbrothers.homebanking.services;

import com.mindhubbrothers.homebanking.dto.AccountDTO;
import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    Account findByNumber(String number);

    Boolean existsByNumber(String number);

    void saveAccount(Account account);

    List<AccountDTO> getAccountsDTO(Client client);

    ResponseEntity<?> createAccount(Client client);

    ResponseEntity<?> getAccounts(Authentication authentication); //get
}
