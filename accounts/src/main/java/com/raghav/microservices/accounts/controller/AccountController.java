package com.raghav.microservices.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.raghav.microservices.accounts.config.AccountsServiceConfig;
import com.raghav.microservices.accounts.entity.Account;
import com.raghav.microservices.accounts.entity.Customer;
import com.raghav.microservices.accounts.model.CustomerDetails;
import com.raghav.microservices.accounts.model.Properties;
import com.raghav.microservices.accounts.service.AccountService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.core.annotation.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountController {
    private final AccountService accountService;

    private final AccountsServiceConfig accountsServiceConfig;

    public AccountController(AccountService accountService, AccountsServiceConfig accountsServiceConfig) {
        this.accountService = accountService;
        this.accountsServiceConfig = accountsServiceConfig;
    }

    @PostMapping(value = "/my-account")
    @Timed(value = "getAccountDetails.time", description = "Time taken to return account details")
    public @ResponseBody ResponseEntity<Account> getAccountDetails(@RequestBody Customer customer) {
        return ResponseEntity.ok(accountService.findByCustomerId(customer.getCustomerId()));
    }

    @PostMapping(value = "/my-customer-details")
//    @CircuitBreaker(name = "detailsForCustomerSupportApp", fallbackMethod = "myCustomerDetailsFallback")
    @Retry(name = "retryForCustomerDetails", fallbackMethod = "myCustomerDetailsFallback")
    public ResponseEntity<CustomerDetails> myCustomerDetails(@RequestHeader("eazybank-correlation-id") String correlationId, @RequestBody Customer customer) {
        return ResponseEntity.ok(accountService.myCustomerDetails(correlationId, customer));
    }

    @GetMapping(value = "/account/properties")
    public @ResponseBody ResponseEntity<String> getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(
                accountsServiceConfig.getMsg(),
                accountsServiceConfig.getBuildVersion(),
                accountsServiceConfig.getMailDetails(),
                accountsServiceConfig.getActiveBranches()
        );
        String jsonStr = ow.writeValueAsString(properties);
        return ResponseEntity.ok(jsonStr);
    }

    private ResponseEntity<CustomerDetails> myCustomerDetailsFallback(String correlationId, Customer customer, Throwable t) {
        return ResponseEntity.ok(accountService.myCustomerDetailsFallback(correlationId, customer));
    }

    @GetMapping("/say-hello")
    @RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
    public String sayHello() {
        Optional<String> podName = Optional.ofNullable(System.getenv("HOSTNAME"));
        return "Hello, Welcome to EazyBank Kubernetes Cluster From: "+podName.get();
    }

    private String sayHelloFallback(Throwable t) {
        return "Hi, Welcome to EazyBank";
    }

}
