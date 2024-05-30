package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.TransactionDTO;
import com.mindhubbrothers.homebanking.dto.TransferDTO;
import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.models.Transaction;
import com.mindhubbrothers.homebanking.models.TypeTransaction;
import com.mindhubbrothers.homebanking.repositories.AccountRepository;
import com.mindhubbrothers.homebanking.repositories.ClientRepository;
import com.mindhubbrothers.homebanking.repositories.TransactionRepository;
import com.mindhubbrothers.homebanking.services.AccountService;
import com.mindhubbrothers.homebanking.services.ClientService;
import com.mindhubbrothers.homebanking.services.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionControllers {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<?> getTransactions(){
        List<Transaction> transactions = transactionService.findAllTransactions();
        if (!transactions.isEmpty()){
            return new ResponseEntity<>(transactions.stream().map(TransactionDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Error, Transactions not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transactions")
    @Transactional
    public ResponseEntity<?> createTransaction(@RequestBody TransferDTO transferDTO){
      try{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (transferDTO.sourceAccountNumber().isBlank()) {
            return new ResponseEntity<>("Missing source account number", HttpStatus.BAD_REQUEST);
        }
        if (transferDTO.destinationAccountNumber().isBlank()) {
            return new ResponseEntity<>("Missing destination account number", HttpStatus.BAD_REQUEST);
        }
        if (transferDTO.amount().isNaN() || transferDTO.amount() == null) {
            return new ResponseEntity<>("Missing amount", HttpStatus.BAD_REQUEST);
        }
        if (transferDTO.description().isBlank()) {
            return new ResponseEntity<>("Missing description", HttpStatus.BAD_REQUEST);
        }
        if (transferDTO.amount() <= 0) {
            return new ResponseEntity<>("Amount must be greater than zero", HttpStatus.BAD_REQUEST);
        }
        if (transferDTO.sourceAccountNumber().equals(transferDTO.destinationAccountNumber())) {
            return new ResponseEntity<>("Source and destination account numbers cannot be the same", HttpStatus.BAD_REQUEST);
        }

        Account sourceAccount = accountService.findByNumber(transferDTO.sourceAccountNumber());
        if (sourceAccount == null){
            return new ResponseEntity<>("Source account does not exist", HttpStatus.NOT_FOUND);
        }

        String currentUserName = authentication.getName();
        Client client = clientService.findByEmail(currentUserName);

        if (client == null){
          return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }

        if (!sourceAccount.getOwner().equals(client)){
           return new ResponseEntity<>("Source account does not belong to the current client", HttpStatus.BAD_REQUEST);
        }

        if (sourceAccount.getBalance() < transferDTO.amount()){
            return new ResponseEntity<>("Insufficient balance in source account", HttpStatus.BAD_REQUEST);
        }

        Account destinationAccount = accountService.findByNumber(transferDTO.destinationAccountNumber());

        if (destinationAccount == null){
            return new ResponseEntity<>("Destination account does not exist", HttpStatus.NOT_FOUND);
        }

        Transaction debitTransaction = new Transaction(LocalDateTime.now(), transferDTO.description() + " " + transferDTO.destinationAccountNumber(), -transferDTO.amount(), TypeTransaction.DEBIT);
        Transaction creditTransaction = new Transaction(LocalDateTime.now(), transferDTO.description() + " " + transferDTO.destinationAccountNumber(), transferDTO.amount(), TypeTransaction.CREDIT);

        debitTransaction.setHostAccount(sourceAccount);
        creditTransaction.setHostAccount(destinationAccount);

        transactionService.saveTransaction(creditTransaction);
        transactionService.saveTransaction(debitTransaction);

        sourceAccount.addTransaction(debitTransaction);
        destinationAccount.addTransaction(creditTransaction);

        sourceAccount.setBalance(sourceAccount.getBalance() - transferDTO.amount());
        destinationAccount.setBalance(destinationAccount.getBalance() + transferDTO.amount());

        accountService.saveAccount(sourceAccount);
        accountService.saveAccount(destinationAccount);


        return new ResponseEntity<>("Transaction succesfull", HttpStatus.CREATED);

      } catch (Exception e){
          System.out.println(e.getMessage());
          return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
      }
    }
}