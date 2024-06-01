package com.mindhubbrothers.homebanking.services;

import com.mindhubbrothers.homebanking.dto.LoanApplicationDTO;
import com.mindhubbrothers.homebanking.models.Loans;
import com.mindhubbrothers.homebanking.dto.LoanDTO;
import com.mindhubbrothers.homebanking.models.Client;

import java.util.List;

public interface LoanService {
    List<LoanDTO> getAllLoans();

    void saveLoan(Loans loan);

    void applyForLoan(LoanApplicationDTO loanApplicationDTO, Client client);
}
