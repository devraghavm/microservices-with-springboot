package com.raghav.microservices.accounts.repository;

import com.raghav.microservices.accounts.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByCustomerId(int customerId);
}
