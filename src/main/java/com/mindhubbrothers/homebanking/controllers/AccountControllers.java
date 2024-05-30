package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.AccountDTO;
import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.AccountRepository;
import com.mindhubbrothers.homebanking.repositories.ClientRepository;
import com.mindhubbrothers.homebanking.services.ClientService;
import com.mindhubbrothers.homebanking.utils.GenerateAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients/current")
public class AccountControllers {

    @Autowired
    private ClientService clientService;

    @Autowired
    private GenerateAccount generateAccount;

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccounts(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Client client = clientService.findByEmail(currentUserName);

        if (client == null){
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        if (client.getAccounts().size() >= 3){
            return new ResponseEntity<>("Error, client already has 3 accounts", HttpStatus.FORBIDDEN);
        }
        generateAccount.createAccount(client);
        return new ResponseEntity<>("Account created succesfully", HttpStatus.CREATED);
    }


    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Client client = clientService.findByEmail(currentUserName);

        if (client == null){
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        List<AccountDTO> accountDTOs = client.getAccounts().stream()
                .map(account->new AccountDTO(account))
                .collect(Collectors.toList());
        return new ResponseEntity<>(accountDTOs,HttpStatus.OK);
    }
}
