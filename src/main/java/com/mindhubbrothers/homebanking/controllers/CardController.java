package com.mindhubbrothers.homebanking.controllers;

import com.mindhubbrothers.homebanking.dto.CardsDTO;
import com.mindhubbrothers.homebanking.models.CardColor;
import com.mindhubbrothers.homebanking.models.CardType;
import com.mindhubbrothers.homebanking.models.Cards;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.CardsRepository;
import com.mindhubbrothers.homebanking.repositories.ClientRepository;
import com.mindhubbrothers.homebanking.utils.GenerateCards;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients/current")
public class CardController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private GenerateCards generateCards;

    @Autowired
    private CardsRepository cardsRepository;

    @PostMapping("/cards")
    public ResponseEntity<?> createCard(
            @RequestParam("color")CardColor color,
            @RequestParam("type")CardType type
            ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Client client = clientRepository.findByEmail(currentUserName);
        if (client == null){
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        Cards newCard = generateCards.generateCard(client, color, type);
        if (newCard == null){
            return new ResponseEntity<>("Error, client already has a card of the same type and color", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Card created succesfully", HttpStatus.CREATED);
    }

    @GetMapping("/cards")
    public ResponseEntity<?> getCards(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Client client = clientRepository.findByEmail(currentUserName);
        if (client == null){
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        List<Cards> cards = cardsRepository.findByOwner(client);
        List<CardsDTO> cardsDTOS = cards.stream()
                .map(card->new CardsDTO(card))
                .collect(Collectors.toList());
        return new ResponseEntity<>(cardsDTOS, HttpStatus.OK);
    }
}

