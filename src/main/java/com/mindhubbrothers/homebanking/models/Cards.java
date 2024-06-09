package com.mindhubbrothers.homebanking.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    private CardType type;

    @Enumerated(value = EnumType.STRING)
    private CardColor color;

    private String number;

    private String cardHolder;

    private LocalDate fromDate;

    private LocalDate thruDate;

    private int securityCode;

    @ManyToOne
    private Client owner;

    public Cards() {
    }

    public Cards(CardType type, CardColor color, String number, LocalDate fromDate, LocalDate thruDate, int securityCode, String cardHolder) {
        this.type = type;
        this.color = color;
        this.number = number;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.securityCode = securityCode;
        this.cardHolder = cardHolder;
    }

    public long getId() {
        return id;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "securityCode=" + securityCode +
                ", thruDate=" + thruDate +
                ", fromDate=" + fromDate +
                ", cardHolder='" + cardHolder + '\'' +
                ", number='" + number + '\'' +
                ", color=" + color +
                ", type=" + type +
                ", id=" + id;
    }
}
