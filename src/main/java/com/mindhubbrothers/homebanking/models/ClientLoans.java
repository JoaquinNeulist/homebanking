package com.mindhubbrothers.homebanking.models;

import jakarta.persistence.*;

@Entity
public class ClientLoans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int payments;

    private double amount;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Loans loans;

    public ClientLoans() {
    }

    public ClientLoans(double amount, int payments) {
        this.amount = amount;
        this.payments = payments;
    }

    public void setLoans(Loans loans) {
        this.loans = loans;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long getId() {
        return id;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public Client getClient() {
        return client;
    }

    public Loans getLoans() {
        return loans;
    }

    @Override
    public String toString() {
        return "ClientLoans{" +
                "id=" + id +
                ", payments=" + payments +
                ", amount=" + amount +
                '}';
    }
}
