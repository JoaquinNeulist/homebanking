package com.mindhubbrothers.homebanking.dto;

import com.mindhubbrothers.homebanking.models.Account;

import java.time.LocalDate;

public class AccountDTO {
    private Long id;

    private String number;

    private LocalDate creationDate;

    private double balance;

    public AccountDTO(Account account) {
    this.id = account.getId();
    this.balance = account.getBalance();
    this.number = account.getNumber();
    this.creationDate = account.getCreationDate();
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }
}