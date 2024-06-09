package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.LoanApplicationDTO;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.services.ClientService;
import com.mindhubbrothers.homebanking.services.LoanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private ClientService clientService;

    @PostMapping("/loans")
    @Transactional
    public ResponseEntity<?> createLoanRequest(@RequestBody LoanApplicationDTO loanApplicationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Client client = clientService.findByEmail(currentUserName);
        if (client == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.FORBIDDEN);
        }
        try {
            loanService.applyForLoan(loanApplicationDTO, client);
            return new ResponseEntity<>("Loan request created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/client/loans")
    public ResponseEntity<?> getLoansByClient(Authentication authentication){
        String currentUserName = authentication.getName();
        Client client = clientService.findByEmail(currentUserName);
        if (client == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(loanService.getLoansByClient(client), HttpStatus.OK);
    }

    @GetMapping("/loans")
    public ResponseEntity<?> getLoans(){
        return new ResponseEntity<>(loanService.getAllLoans(), HttpStatus.OK);
    }
}
