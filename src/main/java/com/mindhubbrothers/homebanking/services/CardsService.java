package com.mindhubbrothers.homebanking.services;

import com.mindhubbrothers.homebanking.models.CardColor;
import com.mindhubbrothers.homebanking.models.CardType;
import com.mindhubbrothers.homebanking.models.Cards;
import com.mindhubbrothers.homebanking.models.Client;

import java.util.List;

public interface CardsService {

    boolean existsByNumber(String number);

    Cards findByOwnerAndColorAndType(Client owner, CardColor color, CardType type);

    void saveCard(Cards card);

    List<Cards> findByOwner(Client owner);
}
