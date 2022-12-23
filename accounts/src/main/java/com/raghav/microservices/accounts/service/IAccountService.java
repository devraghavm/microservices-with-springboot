package com.raghav.microservices.accounts.service;

import com.raghav.microservices.accounts.entity.Account;
import com.raghav.microservices.accounts.entity.Customer;
import com.raghav.microservices.accounts.model.CustomerDetails;

public interface IAccountService {
    Account findByCustomerId(int customerId);

    CustomerDetails myCustomerDetails(String correlationId, Customer customer);

    CustomerDetails myCustomerDetailsFallback(String correlationId, Customer customer);

}
