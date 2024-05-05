package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.AccountDTO;
import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Accounts")
public class AccountControllers {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAccounts(){
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()){
            return new ResponseEntity<>("Error, account not found", HttpStatus.NOT_FOUND);
        }else{
     return new ResponseEntity<>(accounts.stream().map(account -> new AccountDTO(account)).collect(Collectors.toList()), HttpStatus.OK);
        }
    }
}
