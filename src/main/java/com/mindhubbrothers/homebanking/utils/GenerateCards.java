package com.mindhubbrothers.homebanking.utils;

import com.mindhubbrothers.homebanking.models.CardColor;
import com.mindhubbrothers.homebanking.models.CardType;
import com.mindhubbrothers.homebanking.models.Cards;
import com.mindhubbrothers.homebanking.models.Client;
import com.mindhubbrothers.homebanking.repositories.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class GenerateCards {

    @Autowired
    private CardsRepository cardsRepository;

    public Cards generateCard(Client client, CardColor color, CardType type){
        Cards existingCards = cardsRepository.findByOwnerAndColorAndType(client, color, type);
        if (existingCards != null){
            return null; //ya tiene una tarjeta del mismo tipo y color
        }
        String cardNumber = generateCardNumber();
        String cardHolder = client.getFirstName() + " "+ client.getLastName();
        int cvv = generateCVV();
        LocalDate startDate = LocalDate.now();
        LocalDate expirationDate = startDate.plusYears(5);

        Cards newCard = new Cards(type, color, cardNumber, startDate, expirationDate, cvv, cardHolder);
        newCard.setOwner(client);
        cardsRepository.save(newCard);

        return newCard;
    }

    private String generateCardNumber(){
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for(int i=0; i<4; i++){
        for (int j=0; j<4; j++){
            cardNumber.append(random.nextInt(10));
        }
        if (i < 3){
            cardNumber.append("-");
        }
        }
    return cardNumber.toString();
    }

    private int generateCVV(){
        Random random = new Random();
        return random.nextInt(900) + 100;
    }
}
