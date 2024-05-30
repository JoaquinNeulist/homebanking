package com.mindhubbrothers.homebanking.dto;

public record TransferDTO(String sourceAccountNumber, String destinationAccountNumber, Double amount, String description) {
}
