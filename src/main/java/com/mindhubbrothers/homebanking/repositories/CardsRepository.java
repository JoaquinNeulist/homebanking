package com.mindhubbrothers.homebanking.repositories;

import com.mindhubbrothers.homebanking.models.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository <Cards, Long>{
}
