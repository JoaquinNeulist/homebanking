package com.mindhubbrothers.homebanking.services.implement;

import com.mindhubbrothers.homebanking.dto.AccountDTO;
import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.AccountRepository;
import com.mindhubbrothers.homebanking.services.AccountService;
import com.mindhubbrothers.homebanking.services.ClientService;
import com.mindhubbrothers.homebanking.utils.AccountNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientService clientService;

    @Override
    public List<Account> getAllAccounts() {
      return accountRepository.findAll();
    }

    @Override
    public Account findByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public Boolean existsByNumber(String number) {
        return accountRepository.existsByNumber(number);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<AccountDTO> getAccountsDTO(Client client) {
        return accountRepository.findAllByOwner(client).stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> getAccounts(Authentication authentication) {
        String currentUserName = authentication.getName();
        Client client = clientService.findByEmail(currentUserName);
        if (client == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        if (client.getAccounts().isEmpty()) {
            return new ResponseEntity<>("Client has no accounts", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(getAccountsDTO(client), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createAccount(Client client) {
        if (client.getAccounts().size() >= 3) {
            return new ResponseEntity<>("Client already has 3 accounts", HttpStatus.FORBIDDEN);
        }

        String accountNumber;
        do {
            accountNumber = AccountNumberGenerator.generate();
        }
        while (accountRepository.existsByNumber(accountNumber));

        Account newAccount = new Account(accountNumber, LocalDate.now(), 0.0);
        newAccount.setOwner(client);
        accountRepository.save(newAccount);

        clientService.saveClient(client);

        return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
    }


}
