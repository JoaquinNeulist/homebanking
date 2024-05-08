package com.mindhubbrothers.homebanking;

import com.mindhubbrothers.homebanking.models.*;
import com.mindhubbrothers.homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoansRepository loansRepository, ClientLoanRepository clientLoanRepository){
		return (args) -> {
			Client client1 = new Client("Melba","Morel","melba@mindhub.com");
			Client client2 = new Client("Joaquin","Neulist","joaquinneulist@gmail.com");

			Account account1 = new Account("VIN001",LocalDate.now(),5000);
			Account account2 = new Account("VIN002",LocalDate.now().plusDays(1),7500);
			Account account3 = new Account("VIN003",LocalDate.now().minusDays(4),25500);

			Loans personalLoan = new Loans(Set.of(6, 12, 24), 100000, "Personal Loan");
			Loans mortgageLoan = new Loans(Set.of(12, 24, 36, 48, 60), 500000, "Mortgage Loan");
			Loans automotiveLoan = new Loans(Set.of(6, 12, 24, 36), 300000, "Automotive Loan");

			Transaction transaction1 = new Transaction(LocalDateTime.now(),"Dinner transaction", 5500, TypeTransaction.CREDIT);
			Transaction transaction2 = new Transaction(LocalDateTime.now().plusHours(4),"Supermarket transaction", -8000, TypeTransaction.DEBIT);
			Transaction transaction3 = new Transaction(LocalDateTime.now().minusHours(2),"Shopping transaction", 10500, TypeTransaction.CREDIT);

			loansRepository.save(personalLoan);
			loansRepository.save(automotiveLoan);
			loansRepository.save(mortgageLoan);

			ClientLoans clientLoans1 = new ClientLoans(50000, 12);
			ClientLoans clientLoans2 = new ClientLoans(200000, 36);
			ClientLoans clientLoans3 = new ClientLoans(100000, 24);
			ClientLoans clientLoans4 = new ClientLoans(400000, 60);

			personalLoan.addClientLoans(clientLoans1);
			client1.addClientLoans(clientLoans1);

			automotiveLoan.addClientLoans(clientLoans2);
			client1.addClientLoans(clientLoans2);

			personalLoan.addClientLoans(clientLoans3);
			client2.addClientLoans(clientLoans3);

			mortgageLoan.addClientLoans(clientLoans4);
			client2.addClientLoans(clientLoans4);


			account1.addTransaction(transaction1);
			account1.addTransaction(transaction2);
			account2.addTransaction(transaction3);

			client1.addAccount(account1);
			client1.addAccount(account2);
			client2.addAccount(account3);

			clientRepository.save(client1);
			clientRepository.save(client2);

			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);

			clientLoanRepository.save(clientLoans1);
			clientLoanRepository.save(clientLoans2);
			clientLoanRepository.save(clientLoans3);
			clientLoanRepository.save(clientLoans4);

			System.out.println(client1);
			System.out.println(client2);
			System.out.println(personalLoan);

		};
	}
}
