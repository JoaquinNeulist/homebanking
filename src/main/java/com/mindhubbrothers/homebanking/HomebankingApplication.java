package com.mindhubbrothers.homebanking;

import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository){
		return (args) -> {
			Client client1 = new Client("Joaquin","Neulist","joaquinneulist@gmail.com");
			Client client2 = new Client("Andres","Ferrari","andyferrari@gmail.com");

			clientRepository.save(client1);
			clientRepository.save(client2);

			System.out.println(client1);
			System.out.println(client2);

		};
	}
}
