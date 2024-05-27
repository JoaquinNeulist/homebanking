package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.TransactionDTO;
import com.mindhubbrothers.homebanking.models.Transaction;
import com.mindhubbrothers.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Transactions")
public class TransactionControllers {
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/")
    public ResponseEntity<?> getTransactions(){
        List<Transaction> transactions = transactionRepository.findAll();
        if (!transactions.isEmpty()){
            return new ResponseEntity<>(transactions.stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toList()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Error, Transactions not found", HttpStatus.NOT_FOUND);
        }
    }

}
