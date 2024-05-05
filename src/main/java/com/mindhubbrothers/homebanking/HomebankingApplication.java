package com.mindhubbrothers.homebanking;

import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.AccountRepository;
import com.mindhubbrothers.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository){
		return (args) -> {
			Client client1 = new Client("Melba","Morel","melba@mindhub.com");
			Client client2 = new Client("Joaquin","Neulist","joaquinneulist@gmail.com");

			Account account1 = new Account("VIN001",LocalDate.now(),5000);
			Account account2 = new Account("VIN002",LocalDate.now().plusDays(1),7500);
			Account account3 = new Account("VIN003",LocalDate.now().minusDays(4),25500);

			client1.addAccount(account1);
			client1.addAccount(account2);
			client2.addAccount(account3);

			clientRepository.save(client1);
			clientRepository.save(client2);
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);

			System.out.println(client1);
			System.out.println(client2);

		};
	}
}
