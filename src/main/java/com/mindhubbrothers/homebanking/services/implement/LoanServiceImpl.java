package com.mindhubbrothers.homebanking.services.implement;

import com.mindhubbrothers.homebanking.dto.ClientLoanDTO;
import com.mindhubbrothers.homebanking.dto.LoanApplicationDTO;
import com.mindhubbrothers.homebanking.dto.LoanDTO;
import com.mindhubbrothers.homebanking.models.*;
import com.mindhubbrothers.homebanking.repositories.AccountRepository;
import com.mindhubbrothers.homebanking.repositories.ClientLoanRepository;
import com.mindhubbrothers.homebanking.repositories.LoansRepository;
import com.mindhubbrothers.homebanking.repositories.TransactionRepository;
import com.mindhubbrothers.homebanking.services.AccountService;
import com.mindhubbrothers.homebanking.services.LoanService;
import com.mindhubbrothers.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Override
    public List<LoanDTO> getAllLoans() {
        return loansRepository.findAll().stream().map(loans -> new LoanDTO(loans)).collect(Collectors.toList());
    }

    @Override
    public void saveLoan(Loans loan) {
        loansRepository.save(loan);
    }

    @Override
    public List<ClientLoanDTO> getLoansByClient(Client client) {
        return clientLoanRepository.findByClient(client).stream().map(ClientLoanDTO::new).collect(Collectors.toList());
    }

    @Override
    public void applyForLoan(LoanApplicationDTO loanApplicationDTO, Client client) {
        Loans loan = loansRepository.findById(loanApplicationDTO.loanId()).orElseThrow(() -> new RuntimeException("Loan not found"));
        boolean alreadyHasLoan = clientLoanRepository.findByClientAndLoans(client, loan).isPresent();
        if (alreadyHasLoan){
            throw new RuntimeException("Client already has this loan");
        }
        double finalAmount = 0;
        double installments = loanApplicationDTO.amount()/loanApplicationDTO.payments();
        if (loanApplicationDTO.amount() <= 0 || loanApplicationDTO.payments() <= 0){
            throw new RuntimeException("Amount and payments must be greater than 0");
        }
        if (loanApplicationDTO.amount() > loan.getMaxAmount()){
            throw new RuntimeException("Amount must be less than or equal to " + loan.getMaxAmount());
        }
        if (!loan.getPayments().contains(loanApplicationDTO.payments())){
            throw new RuntimeException("Number of installments must be one of " + loan.getPayments());
        }
        Account destinationAccount = accountService.findByNumber(loanApplicationDTO.destinationAccountNumber());
        if (destinationAccount == null){
            throw new RuntimeException("Destination account not found");
        }
        if (!destinationAccount.getOwner().equals(client)){
            throw new RuntimeException("Destination account does not belong to client");
        }
        if (loanApplicationDTO.payments().equals(12)){
            finalAmount = loanApplicationDTO.amount() * 1.2;
        }
        if (loanApplicationDTO.payments() > 12){
            finalAmount = loanApplicationDTO.amount() * 1.25;
        }
        if (loanApplicationDTO.payments() < 12){
            finalAmount = loanApplicationDTO.amount() * 1.15;
        }
        ClientLoans clientLoans = new ClientLoans(finalAmount, loanApplicationDTO.payments());
        clientLoans.setClient(client);
        clientLoans.setLoans(loan);
        clientLoanRepository.save(clientLoans);

        Transaction creditTransaction = new Transaction(LocalDateTime.now(), loan.getName() + " approved", loanApplicationDTO.amount(), TypeTransaction.CREDIT);
        creditTransaction.setHostAccount(destinationAccount);
        transactionService.saveTransaction(creditTransaction);

        destinationAccount.setBalance(destinationAccount.getBalance() + loanApplicationDTO.amount());
        accountService.saveAccount(destinationAccount);
    }
}
