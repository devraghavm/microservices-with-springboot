package com.raghav.microservices.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.raghav.microservices.cards.config.CardsServiceConfig;
import com.raghav.microservices.cards.entity.Card;
import com.raghav.microservices.cards.entity.Customer;
import com.raghav.microservices.cards.model.Properties;
import com.raghav.microservices.cards.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    private final CardService cardService;

    private final CardsServiceConfig cardsServiceConfig;

    public CardController(CardService cardService, CardsServiceConfig cardsServiceConfig) {
        this.cardService = cardService;
        this.cardsServiceConfig = cardsServiceConfig;
    }

    @PostMapping(value = "my-cards")
    public @ResponseBody ResponseEntity<List<Card>> getCardDetails(@RequestHeader("eazybank-correlation-id") String correlationId, @RequestBody Customer customer) {
        return ResponseEntity.ok(cardService.findByCustomerId(customer.getCustomerId()));
    }

    @GetMapping(value = "/card/properties")
    public @ResponseBody ResponseEntity<String> getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(
                cardsServiceConfig.getMsg(),
                cardsServiceConfig.getBuildVersion(),
                cardsServiceConfig.getMailDetails(),
                cardsServiceConfig.getActiveBranches()
        );
        String jsonStr = ow.writeValueAsString(properties);
        return ResponseEntity.ok(jsonStr);
    }
}