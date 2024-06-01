package com.mindhubbrothers.homebanking.repositories;

import com.mindhubbrothers.homebanking.models.CardColor;
import com.mindhubbrothers.homebanking.models.CardType;
import com.mindhubbrothers.homebanking.models.Cards;
import com.mindhubbrothers.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository <Cards, Long>{
    Cards findByOwnerAndColorAndType(Client owner, CardColor color, CardType type);
    List<Cards> findByOwner(Client owner);
    boolean existsByNumber(String number);

}
