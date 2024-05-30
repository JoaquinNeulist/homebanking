package com.mindhubbrothers.homebanking.services.implement;

import com.mindhubbrothers.homebanking.dto.TransactionDTO;
import com.mindhubbrothers.homebanking.models.Transaction;
import com.mindhubbrothers.homebanking.repositories.TransactionRepository;
import com.mindhubbrothers.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }
}
