package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.AccountDTO;
import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.services.AccountService;
import com.mindhubbrothers.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients/current")
public class AccountControllers {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccounts(Authentication authentication){
        String email = authentication.getName();
        Client client = clientService.findByEmail(email);
        return accountService.createAccount(client);
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts(Authentication authentication){
        return accountService.getAccounts(authentication);
    }

    @GetMapping("/accounts/")
    public ResponseEntity<?> getAllAccounts(){
        List<AccountDTO> accounts = accountService.getAllAccounts().stream().map(AccountDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
