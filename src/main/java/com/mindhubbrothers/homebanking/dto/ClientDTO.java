package com.mindhubbrothers.homebanking.dto;

import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.dto.AccountDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private long id;
    private String firstName;
    private String clientEmail;
    private Set<AccountDTO> accounts = new HashSet<>();

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.clientEmail = client.getClientEmail();
        this.accounts = client.getAccounts()
                .stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public String getFirstName() {
        return firstName;
    }
}
