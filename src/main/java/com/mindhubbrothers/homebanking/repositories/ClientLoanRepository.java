package com.mindhubbrothers.homebanking.repositories;

import com.mindhubbrothers.homebanking.models.Account;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.models.ClientLoans;
import com.mindhubbrothers.homebanking.models.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientLoanRepository extends JpaRepository<ClientLoans, Long> {

    List<ClientLoans> findByClient(Client client);

    Optional<ClientLoans> findByClientAndLoans(Client client, Loans loan);
}
