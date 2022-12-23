package com.raghav.microservices.accounts.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Loan {
    private int loanNumber;
    private int customerId;
    private LocalDateTime startDt;
    private String loanType;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
    private String createDt;
}
