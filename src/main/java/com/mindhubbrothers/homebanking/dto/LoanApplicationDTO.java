package com.mindhubbrothers.homebanking.dto;

public record LoanApplicationDTO(long loanId, double amount, Integer payments, String destinationAccountNumber) {
}
