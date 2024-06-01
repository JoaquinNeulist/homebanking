package com.mindhubbrothers.homebanking.dto;

import com.mindhubbrothers.homebanking.models.CardColor;
import com.mindhubbrothers.homebanking.models.CardType;

public record CardCreationDTO(CardType type, CardColor color) {
}
