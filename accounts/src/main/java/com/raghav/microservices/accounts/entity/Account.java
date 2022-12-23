package com.raghav.microservices.accounts.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@ToString
@Entity(name = "account")
public class Account {
    private int customerId;
    @Id
    private long accountNumber;
    private String accountType;
    private String branchAddress;
    private LocalDateTime createDt;

}
