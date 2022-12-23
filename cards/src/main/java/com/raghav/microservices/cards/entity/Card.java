package com.raghav.microservices.cards.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@ToString
@Entity(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cardId;
    private int customerId;
    private String cardNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
    private LocalDateTime createDt;
}
