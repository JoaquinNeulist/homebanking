package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients/current")
public class AccountControllers {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccounts(Authentication authentication){
        return accountService.createAccount(authentication);
    }


    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts(Authentication authentication){
        return accountService.getAccounts(authentication);
    }
}
