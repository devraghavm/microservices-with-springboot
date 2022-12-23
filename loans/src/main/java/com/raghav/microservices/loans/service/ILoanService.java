package com.raghav.microservices.loans.service;

import com.raghav.microservices.loans.entity.Loan;

import java.util.List;

public interface ILoanService {
    List<Loan> findByCustomerId(int customerId);
}
