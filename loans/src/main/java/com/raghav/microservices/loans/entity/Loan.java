package com.raghav.microservices.loans.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@ToString
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanNumber;
    private int customerId;
    private LocalDateTime startDt;
    private String loanType;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
    private String createDt;

}
