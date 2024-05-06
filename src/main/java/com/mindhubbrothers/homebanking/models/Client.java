package com.mindhubbrothers.homebanking.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
