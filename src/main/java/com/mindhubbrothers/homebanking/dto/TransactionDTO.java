package com.mindhubbrothers.homebanking.dto;

import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Transaction;
import com.mindhubbrothers.homebanking.models.TypeTransaction;

import java.time.LocalDateTime;

public class TransactionDTO {

    private long id;

    private TypeTransaction type;

    private double amount;

    private String description;

    private LocalDateTime date;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public TypeTransaction getType() {
        return type;
    }
}
