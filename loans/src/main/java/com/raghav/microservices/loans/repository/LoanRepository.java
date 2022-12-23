package com.raghav.microservices.loans.repository;

import com.raghav.microservices.loans.entity.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);
}
