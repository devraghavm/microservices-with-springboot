package com.raghav.microservices.cards.service;

import com.raghav.microservices.cards.entity.Card;

import java.util.List;

public interface ICardService {
    List<Card> findByCustomerId(int customerId);
}
