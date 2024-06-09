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

    private String email;

    public String password;

    private Boolean isAdmin;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private Set<ClientLoans> clientLoansSet = new HashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<Cards> cards = new HashSet<>();

    public Client() {
    }

    public Client(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Client(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isAdmin = false;
    }


    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Set<ClientLoans> getClientLoansSet() {
        return clientLoansSet;
    }

    public Set<Cards> getCards() {
        return cards;
    }

    public void setClientLoansSet(Set<ClientLoans> clientLoansSet) {
        this.clientLoansSet = clientLoansSet;
    }

    public void addAccount(Account account){
        account.setOwner(this);
        accounts.add(account);
    }


    public void addClientLoans (ClientLoans clientLoans){
        clientLoans.setClient(this);
        clientLoansSet.add(clientLoans);
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
                ", clientEmail='" + email + '\'' +
                ", accounts=" + accounts +
                ", clientLoansSet=" + clientLoansSet +
                ", cards=" + cards +
                '}';
    }
}
