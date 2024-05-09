package com.mindhubbrothers.homebanking.dto;

import com.mindhubbrothers.homebanking.models.CardColor;
import com.mindhubbrothers.homebanking.models.CardType;
import com.mindhubbrothers.homebanking.models.Cards;
import com.mindhubbrothers.homebanking.models.Client;

import java.time.LocalDate;

public class CardsDTO {
    private long id;

    private CardType type;

    private CardColor color;

    private String number;

    private LocalDate fromDate;

    private LocalDate thruDate;

    private String cardHolder;

    private int securityCode;

    public CardsDTO(Cards cards) {
        this.id = cards.getId();
        this.type = cards.getType();
        this.color = cards.getColor();
        this.number = cards.getNumber();
        this.fromDate = cards.getFromDate();
        this.thruDate = cards.getThruDate();
        this.securityCode = cards.getSecurityCode();
        this.cardHolder = cards.getCardHolder();
    }

    public long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public String getNumber() {
        return number;
    }

    public CardColor getColor() {
        return color;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public CardType getType() {
        return type;
    }
}
