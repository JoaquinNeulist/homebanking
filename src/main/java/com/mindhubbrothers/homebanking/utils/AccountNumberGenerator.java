package com.mindhubbrothers.homebanking.utils;

import com.mindhubbrothers.homebanking.repositories.AccountRepository;

import java.util.Random;

public class AccountNumberGenerator {

    public static String generate() {
        Random random = new Random();
        String accountNumber;
        int randomNumber = random.nextInt(9000) + 1000;
        accountNumber = "VIN-" + randomNumber;
        return accountNumber;
    }
}
