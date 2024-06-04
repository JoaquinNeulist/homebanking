package com.mindhubbrothers.homebanking.dto;

import com.mindhubbrothers.homebanking.models.Loans;

import java.util.HashSet;
import java.util.Set;

public class LoanDTO {

    private long id;

    private String name;

    private double maxAmount;

    private Set<Integer> payments = new HashSet<>();

    public LoanDTO(Loans loans) {
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

    public double getMaxAmount() {
        return maxAmount;
    }

    public Set<Integer> getPayments() {
        return payments;
    }
}
