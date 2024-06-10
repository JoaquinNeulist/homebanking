package com.mindhubbrothers.homebanking.services.implement;

import com.mindhubbrothers.homebanking.dto.CardCreationDTO;
import com.mindhubbrothers.homebanking.dto.CardsDTO;
import com.mindhubbrothers.homebanking.models.CardColor;
import com.mindhubbrothers.homebanking.models.CardType;
import com.mindhubbrothers.homebanking.models.Cards;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.CardsRepository;
import com.mindhubbrothers.homebanking.services.CardsService;
import com.mindhubbrothers.homebanking.services.ClientService;
import com.mindhubbrothers.homebanking.utils.CVVGenerator;
import com.mindhubbrothers.homebanking.utils.CardNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardsServiceImpl implements CardsService {
    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private ClientService clientService;

    @Override
    public List<Cards> findAll() {
        return cardsRepository.findAll();
    }

    @Override
    public List<CardsDTO> findAllDTOs() {
        return cardsRepository.findAll().stream().map(card -> new CardsDTO(card)).collect(Collectors.toList());
    }

    @Override
    public Cards findById(Long id) {
        return cardsRepository.findById(id).orElse(null);
    }

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

    @Override
    public ResponseEntity<?> getCards(Authentication authentication) {
        String currentUserName = authentication.getName();
        Client client = clientService.findByEmail(currentUserName);
        if (client == null){
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        List<Cards> cards = cardsRepository.findByOwner(client);
        List<CardsDTO> cardsDTOS = cards.stream()
                .map(CardsDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(cardsDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createCard(CardCreationDTO cardCreationDTO, Authentication authentication) {
        String currentUserName = authentication.getName();
        Client client = clientService.findByEmail(currentUserName);
        if (client == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }

        Cards existingCard = cardsRepository.findByOwnerAndColorAndType(client, cardCreationDTO.color(), cardCreationDTO.type());
        if (existingCard != null) {
            return new ResponseEntity<>("Error, card already exists", HttpStatus.FORBIDDEN);
        }

        String cardNumber;
        do {
            cardNumber = CardNumberGenerator.generate();
        }
        while (cardsRepository.existsByNumber(cardNumber));
        String cardHolder = client.getFirstName() + " " + client.getLastName();
        int cvv = CVVGenerator.generate();
        LocalDate startDate = LocalDate.now();
        LocalDate expirationDate = startDate.plusYears(5);

        Cards newCard = new Cards(cardCreationDTO.type(), cardCreationDTO.color(), cardNumber, startDate, expirationDate, cvv, cardHolder);
        newCard.setOwner(client);
        cardsRepository.save(newCard);

        return new ResponseEntity<>("Card created successfully", HttpStatus.CREATED);
    }

}
