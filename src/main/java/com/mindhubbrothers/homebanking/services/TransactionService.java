package com.mindhubbrothers.homebanking.services;

import com.mindhubbrothers.homebanking.dto.TransactionDTO;
import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Transaction;

import java.util.List;

public interface TransactionService {

    void saveTransaction(Transaction transaction);

    List<Transaction> findAllTransactions();
}
