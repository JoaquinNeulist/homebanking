package com.mindhubbrothers.homebanking.utils;

import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class GenerateAccount {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Client client){
        String accountNumber = generateAccountNumber();
        Account newAccount = new Account(accountNumber, LocalDate.now(), 0.0);
        newAccount.setOwner(client);
        accountRepository.save(newAccount);
        return newAccount;
    }

    private String generateAccountNumber(){
        Random random = new Random();
        int randomNumber = random.nextInt(9000)+1000;
        return "VIN-"+randomNumber;
    }

}

