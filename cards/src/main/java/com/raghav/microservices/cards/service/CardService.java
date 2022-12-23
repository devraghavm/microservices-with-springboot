package com.raghav.microservices.cards.service;

import com.raghav.microservices.cards.entity.Card;
import com.raghav.microservices.cards.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CardService implements ICardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> findByCustomerId(int customerId) {
        log.info("getCardDetails() method started");
        List<Card> cards = Optional.ofNullable(cardRepository.findByCustomerId(customerId)).orElse(null);
        log.info("getCardDetails() method ended");
        return cards;
    }
}
