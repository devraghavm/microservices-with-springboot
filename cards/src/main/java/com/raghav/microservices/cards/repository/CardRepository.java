package com.raghav.microservices.cards.repository;

import com.raghav.microservices.cards.entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByCustomerId(int customerId);
}
