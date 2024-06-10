package com.mindhubbrothers.homebanking.utils;
import com.mindhubbrothers.homebanking.repositories.CardsRepository;

import java.util.Random;

public class CardNumberGenerator {

    public static String generate(  ) {
        Random random = new Random();
        String cardNumber;

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    stringBuilder.append(random.nextInt(10));
                }
                if (i < 3) {
                    stringBuilder.append("-");
                }
            }
            cardNumber = stringBuilder.toString();
        return cardNumber;
        }
    }


