package com.mindhubbrothers.homebanking.services;

import com.mindhubbrothers.homebanking.dto.TransferDTO;
import com.mindhubbrothers.homebanking.models.Transaction;
import org.springframework.http.ResponseEntity;


public interface TransactionService {

    void saveTransaction(Transaction transaction);

    ResponseEntity<?> createTransaction(TransferDTO transferDTO);

    ResponseEntity<?> getTransactions();
}
