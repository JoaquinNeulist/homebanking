package com.mindhubbrothers.homebanking.services;

import com.mindhubbrothers.homebanking.dto.CardCreationDTO;
import com.mindhubbrothers.homebanking.models.CardColor;
import com.mindhubbrothers.homebanking.models.CardType;
import com.mindhubbrothers.homebanking.models.Cards;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.dto.CardsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CardsService {

    List<Cards> findAll();

    List<CardsDTO> findAllDTOs();

    Cards findById(Long id);

    boolean existsByNumber(String number);

    Cards findByOwnerAndColorAndType(Client owner, CardColor color, CardType type);

    void saveCard(Cards card);

    List<Cards> findByOwner(Client owner);

    ResponseEntity<?> createCard(CardCreationDTO cardCreationDTO, Authentication authentication);

    ResponseEntity<?> getCards(Authentication authentication);
}
