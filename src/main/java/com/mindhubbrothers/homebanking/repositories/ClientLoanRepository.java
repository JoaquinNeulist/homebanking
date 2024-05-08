package com.mindhubbrothers.homebanking.repositories;

import com.mindhubbrothers.homebanking.models.ClientLoans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientLoanRepository extends JpaRepository<ClientLoans, Long> {
}
