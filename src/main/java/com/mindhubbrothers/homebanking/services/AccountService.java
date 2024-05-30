package com.mindhubbrothers.homebanking.services;

import com.mindhubbrothers.homebanking.models.Account;

import java.util.List;

public interface AccountService {

    List <Account> getListAccounts();

    Account findById(Long id);

    Account findByNumber(String number);

    Boolean existsByNumber(String number);

    void saveAccount(Account account);
}
