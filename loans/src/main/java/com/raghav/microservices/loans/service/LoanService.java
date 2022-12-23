package com.raghav.microservices.loans.service;

import com.raghav.microservices.loans.entity.Loan;
import com.raghav.microservices.loans.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LoanService implements ILoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<Loan> findByCustomerId(int customerId) {
        log.info("getLoanDetails() method started");
        List<Loan> loans = Optional.ofNullable(loanRepository.findByCustomerIdOrderByStartDtDesc(customerId)).orElse(null);
        log.info("getLoanDetails() method ended");
        return loans;
    }
}
