package com.mindhubbrothers.homebanking.dto;

import com.mindhubbrothers.homebanking.models.ClientLoans;
import com.mindhubbrothers.homebanking.models.Loans;

import java.util.HashSet;
import java.util.Set;

public class LoansDTO {

    private long id;

    private String name;

    private int maxAmount;

    private Set<Integer> payments = new HashSet<>();

    public LoansDTO(Loans loans) {
        this.id = loans.getId();
        this.name = loans.getName();
        this.maxAmount = loans.getMaxAmount();
        this.payments = loans.getPayments();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public Set<Integer> getPayments() {
        return payments;
    }
}
