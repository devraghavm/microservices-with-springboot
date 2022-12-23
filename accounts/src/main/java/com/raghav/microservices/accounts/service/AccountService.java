package com.raghav.microservices.accounts.service;

import com.raghav.microservices.accounts.entity.Account;
import com.raghav.microservices.accounts.entity.Customer;
import com.raghav.microservices.accounts.model.Card;
import com.raghav.microservices.accounts.model.CustomerDetails;
import com.raghav.microservices.accounts.model.Loan;
import com.raghav.microservices.accounts.repository.AccountRepository;
import com.raghav.microservices.accounts.service.client.CardsFeignClient;
import com.raghav.microservices.accounts.service.client.LoansFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;

    private final CardsFeignClient cardsFeignClient;

    private final LoansFeignClient loansFeignClient;

    public AccountService(AccountRepository accountRepository, CardsFeignClient cardsFeignClient, LoansFeignClient loansFeignClient) {
        this.accountRepository = accountRepository;
        this.cardsFeignClient = cardsFeignClient;
        this.loansFeignClient = loansFeignClient;
    }

    @Override
    public Account findByCustomerId(int customerId) {
        return Optional.ofNullable(accountRepository.findByCustomerId(customerId)).orElse(null);
    }

    @Override
    public CustomerDetails myCustomerDetails(String correlationId, Customer customer) {
        log.info("myCustomerDetails() method started");
        Account account = findByCustomerId(customer.getCustomerId());
        List<Loan> loans = loansFeignClient.getLoanDetails(correlationId, customer);
        List<Card> cards = cardsFeignClient.getCardDetails(correlationId, customer);

        CustomerDetails customerDetails = CustomerDetails.builder()
                                                         .account(account)
                                                         .cards(cards)
                                                         .loans(loans)
                                                         .build();

        log.info("myCustomerDetails() method ended");
        return customerDetails;
    }

    @Override
    public CustomerDetails myCustomerDetailsFallback(String correlationId, Customer customer) {
        Account account = findByCustomerId(customer.getCustomerId());
        List<Loan> loans = loansFeignClient.getLoanDetails(correlationId, customer);
        CustomerDetails customerDetails = CustomerDetails.builder()
                                                         .account(account)
                                                         .loans(loans)
                                                         .build();

        return customerDetails;
    }
}
