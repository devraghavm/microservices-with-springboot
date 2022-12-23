package com.raghav.microservices.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.raghav.microservices.loans.config.LoansServiceConfig;
import com.raghav.microservices.loans.entity.Customer;
import com.raghav.microservices.loans.entity.Loan;
import com.raghav.microservices.loans.model.Properties;
import com.raghav.microservices.loans.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    private final LoanService loanService;

    private final LoansServiceConfig loansServiceConfig;

    public LoanController(LoanService loanService, LoansServiceConfig loansServiceConfig) {
        this.loanService = loanService;
        this.loansServiceConfig = loansServiceConfig;
    }

    @PostMapping("/my-loans")
    public @ResponseBody ResponseEntity<List<Loan>> getLoansDetails(@RequestHeader("eazybank-correlation-id") String correlationId, @RequestBody Customer customer) {
//        System.out.println("Invoking Loans Microservice With Correlation ID: " + correlationId);
        return ResponseEntity.ok(loanService.findByCustomerId(customer.getCustomerId()));
    }

    @GetMapping(value = "/loan/properties")
    public @ResponseBody ResponseEntity<String> getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(
                loansServiceConfig.getMsg(),
                loansServiceConfig.getBuildVersion(),
                loansServiceConfig.getMailDetails(),
                loansServiceConfig.getActiveBranches()
        );
        String jsonStr = ow.writeValueAsString(properties);
        return ResponseEntity.ok(jsonStr);
    }
}
