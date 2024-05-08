package com.mindhubbrothers.homebanking.dto;

import com.mindhubbrothers.homebanking.models.Account;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {

    private Long id;

    private String number;

    private LocalDate creationDate;

    private double balance;

    private List<TransactionDTO> transactions = new ArrayList<>();

    public AccountDTO(Account account) {
    this.id = account.getId();
    this.balance = account.getBalance();
    this.number = account.getNumber();
    this.creationDate = account.getCreationDate();
    this.transactions = account.getTransactions()
            .stream()
            .map(transaction -> new TransactionDTO(transaction))
            .collect(Collectors.toList());
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

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }
}