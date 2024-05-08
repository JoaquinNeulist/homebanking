package com.mindhubbrothers.homebanking.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int maxAmount;

    @ElementCollection
    private Set<Integer> payments = new HashSet<>();

    @OneToMany(mappedBy = "loans", fetch = FetchType.EAGER)
    private Set<ClientLoans> clientLoansSet = new HashSet<>();

    public Loans() {
    }

    public Loans(Set<Integer> payments, int maxAmount, String name) {
        this.payments = payments;
        this.maxAmount = maxAmount;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Set<Integer> getPayments() {
        return payments;
    }

    public void setPayments(Set<Integer> payments) {
        this.payments = payments;
    }

    public Set<ClientLoans> getClientLoansSet() {
        return clientLoansSet;
    }

    public void setClientLoansSet(Set<ClientLoans> clientLoansSet) {
        this.clientLoansSet = clientLoansSet;
    }

    public void addClientLoans (ClientLoans clientLoans){
        clientLoans.setLoans(this);
        clientLoansSet.add(clientLoans);
    }

    @Override
    public String toString() {
        return "Loans{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxAmount=" + maxAmount +
                ", payments=" + payments +
                '}';
    }
}
