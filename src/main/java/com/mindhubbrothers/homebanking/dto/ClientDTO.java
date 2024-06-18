package com.mindhubbrothers.homebanking.dto;


import com.mindhubbrothers.homebanking.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private Set<AccountDTO> accountsDTO = new HashSet<>();

    private Set<ClientLoanDTO> clientLoanDTOSet = new HashSet<>();

    private Set<CardsDTO> cardsDTOS = new HashSet<>();

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accountsDTO = client.getAccounts()
                .stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toSet());
        this.clientLoanDTOSet = client.getClientLoansSet()
                .stream()
                .map(clientLoan -> new ClientLoanDTO(clientLoan))
                .collect(Collectors.toSet());
        this.cardsDTOS = client.getCards().stream()
                .map(card -> new CardsDTO(card))
                .collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public Set<AccountDTO> getAccounts() {
        return accountsDTO;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<ClientLoanDTO> getLoans() {
        return clientLoanDTOSet;
    }

    public String getFirstName() {
        return firstName;
    }

    public Set<CardsDTO> getCards() {
        return cardsDTOS;
    }
}
