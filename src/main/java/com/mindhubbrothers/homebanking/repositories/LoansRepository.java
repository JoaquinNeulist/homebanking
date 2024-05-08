package com.mindhubbrothers.homebanking.repositories;

import com.mindhubbrothers.homebanking.models.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
}
