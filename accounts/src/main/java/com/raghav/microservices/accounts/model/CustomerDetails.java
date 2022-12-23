package com.raghav.microservices.accounts.model;

import com.raghav.microservices.accounts.entity.Account;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class CustomerDetails {
    private Account account;
    private List<Card> cards;
    private List<Loan> loans;
}
