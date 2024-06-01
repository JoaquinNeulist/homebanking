package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.CardCreationDTO;
import com.mindhubbrothers.homebanking.services.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients/current")
public class CardController {

    @Autowired
    private CardsService cardsService;

    @PostMapping("/cards")
    public ResponseEntity<?> createCard( @RequestBody CardCreationDTO cardCreationDTO, Authentication authentication){
        return cardsService.createCard(cardCreationDTO, authentication);
    }

    @GetMapping("/cards")
    public ResponseEntity<?> getCards(Authentication authentication){
        return cardsService.getCards(authentication);
    }
}

