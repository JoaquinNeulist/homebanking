package com.mindhubbrothers.homebanking.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String clientEmail;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ClientLoans> clientLoansSet = new HashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<Cards> cards = new HashSet<>();

    public Client() {
    }

    public Client(String firstName, String lastName, String clientEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.clientEmail = clientEmail;
    }

    public long getId() {
        return id;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account){
        account.setOwner(this);
        accounts.add(account);
    }

    public Set<ClientLoans> getClientLoansSet() {
        return clientLoansSet;
    }

    public void setClientLoansSet(Set<ClientLoans> clientLoansSet) {
        this.clientLoansSet = clientLoansSet;
    }

    public void addClientLoans (ClientLoans clientLoans){
        clientLoans.setClient(this);
        clientLoansSet.add(clientLoans);
    }

    public Set<Cards> getCards() {
        return cards;
    }

    public void addCards(Cards card) {
        card.setOwner(this);
        cards.add(card);
        card.setCardHolder(this.firstName +" "+ this.lastName);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", accounts=" + accounts +
                ", clientLoansSet=" + clientLoansSet +
                ", cards=" + cards +
                '}';
    }
}
