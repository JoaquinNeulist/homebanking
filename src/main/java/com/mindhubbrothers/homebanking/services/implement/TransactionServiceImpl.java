package com.mindhubbrothers.homebanking.services.implement;

import com.mindhubbrothers.homebanking.dto.TransactionDTO;
import com.mindhubbrothers.homebanking.dto.TransferDTO;
import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.models.Transaction;
import com.mindhubbrothers.homebanking.models.TypeTransaction;
import com.mindhubbrothers.homebanking.repositories.TransactionRepository;
import com.mindhubbrothers.homebanking.services.AccountService;
import com.mindhubbrothers.homebanking.services.ClientService;
import com.mindhubbrothers.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public ResponseEntity<?> createTransaction(TransferDTO transferDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (transferDTO.sourceAccountNumber().isBlank()) {
                return new ResponseEntity<>("Missing source account number", HttpStatus.FORBIDDEN);
            }
            if (transferDTO.destinationAccountNumber().isBlank()){
                return new ResponseEntity<>("Missing destination account number", HttpStatus.FORBIDDEN);
            }
            if (transferDTO.amount().isNaN() || transferDTO.amount() == null) {
                return new ResponseEntity<>("Missing amount", HttpStatus.FORBIDDEN);
            }
            if (transferDTO.description().isBlank()) {
                return new ResponseEntity<>("Missing description", HttpStatus.FORBIDDEN);
            }
            if (transferDTO.amount() <= 0) {
                return new ResponseEntity<>("Amount must be greater than zero", HttpStatus.FORBIDDEN);
            }
            if (transferDTO.sourceAccountNumber().equals(transferDTO.destinationAccountNumber())) {
                return new ResponseEntity<>("Source and destination account numbers cannot be the same", HttpStatus.FORBIDDEN);
            }
            Account sourceAccount = accountService.findByNumber(transferDTO.sourceAccountNumber());
            if (sourceAccount == null) {
                return new ResponseEntity<>("Source account does not exist", HttpStatus.FORBIDDEN);
            }
            String currentUserName = authentication.getName();
            Client client = clientService.findByEmail(currentUserName);
            if (client == null) {
                return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
            }
            if (!sourceAccount.getOwner().equals(client)) {
                return new ResponseEntity<>("Source account does not belong to the client", HttpStatus.FORBIDDEN);
            }
            if (sourceAccount.getBalance() < transferDTO.amount()) {
                return new ResponseEntity<>("Insufficient balance", HttpStatus.FORBIDDEN);
            }
            Account destinationAccount = accountService.findByNumber(transferDTO.destinationAccountNumber());
            if (destinationAccount == null) {
                return new ResponseEntity<>("Destination account does not exist", HttpStatus.FORBIDDEN);
            }

            Transaction debitTransaction = new Transaction(LocalDateTime.now(), transferDTO.description() + " " + transferDTO.destinationAccountNumber(), -transferDTO.amount(), TypeTransaction.DEBIT);
            Transaction creditTransaction = new Transaction(LocalDateTime.now(), transferDTO.description() + " " + transferDTO.destinationAccountNumber(), transferDTO.amount(), TypeTransaction.CREDIT);

            transactionRepository.save(debitTransaction);
            transactionRepository.save(creditTransaction);

            sourceAccount.setBalance(sourceAccount.getBalance() - transferDTO.amount());
            destinationAccount.setBalance(destinationAccount.getBalance() + transferDTO.amount());

            sourceAccount.addTransaction(debitTransaction);
            destinationAccount.addTransaction(creditTransaction);

            accountService.saveAccount(sourceAccount);
            accountService.saveAccount(destinationAccount);

            return new ResponseEntity<>("Transaction succesfull", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<?> getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()){
            return new ResponseEntity<>("Error, transactions not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactions.stream().map(TransactionDTO::new).toList(), HttpStatus.OK);
    }
}
