package com.raghav.microservices.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RefreshScope
@ComponentScan(basePackages = "com.raghav.microservices")
public class CardsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }
}
