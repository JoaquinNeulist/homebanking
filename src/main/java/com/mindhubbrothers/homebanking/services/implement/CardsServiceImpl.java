package com.mindhubbrothers.homebanking.services.implement;

import com.mindhubbrothers.homebanking.models.CardColor;
import com.mindhubbrothers.homebanking.models.CardType;
import com.mindhubbrothers.homebanking.models.Cards;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.CardsRepository;
import com.mindhubbrothers.homebanking.services.CardsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CardsServiceImpl implements CardsService {
    @Autowired
    private CardsRepository cardsRepository;
    @Override
    public boolean existsByNumber(String number) {
        return cardsRepository.existsByNumber(number);
    }

    @Override
    public Cards findByOwnerAndColorAndType(Client owner, CardColor color, CardType type) {
        return cardsRepository.findByOwnerAndColorAndType(owner, color, type);
    }

    @Override
    public void saveCard(Cards card) {
        cardsRepository.save(card);
    }

    @Override
    public List<Cards> findByOwner(Client owner) {
        return cardsRepository.findByOwner(owner);
    }
}
