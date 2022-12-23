package com.raghav.microservices.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RefreshScope
@ComponentScan(basePackages = "com.raghav.microservices")
public class LoansApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }
}
