package com.mindhubbrothers.homebanking.repositories;

import com.mindhubbrothers.homebanking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByNumber(String number);
}
