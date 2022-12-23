package com.raghav.microservices.accounts.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Card {
    private int cardId;
    private int customerId;
    private String cardNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
    private LocalDateTime createDt;
}
