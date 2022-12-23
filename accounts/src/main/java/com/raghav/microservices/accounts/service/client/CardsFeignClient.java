package com.raghav.microservices.accounts.service.client;

import com.raghav.microservices.accounts.entity.Customer;
import com.raghav.microservices.accounts.model.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("cards")
public interface CardsFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "my-cards", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Card> getCardDetails(@RequestHeader("eazybank-correlation-id") String correlationId, @RequestBody Customer customer);
}
