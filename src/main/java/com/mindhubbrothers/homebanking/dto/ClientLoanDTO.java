package com.mindhubbrothers.homebanking.dto;


import com.mindhubbrothers.homebanking.models.ClientLoans;


public class ClientLoanDTO {

    private long id;

    private long loanId;

    private String loanName;

    private int payments;

    private double amount;

    public ClientLoanDTO(ClientLoans clientLoans) {
        this.id = clientLoans.getId();
        this.loanId = clientLoans.getLoans().getId();
        this.loanName = clientLoans.getLoans().getName();
        this.payments = clientLoans.getPayments();
        this.amount = clientLoans.getAmount();
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public int getPayments() {
        return payments;
    }

    public String getLoanName() {
        return loanName;
    }

    public long getLoanId() {
        return loanId;
    }
}
