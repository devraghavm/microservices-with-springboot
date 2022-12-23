package com.raghav.microservices.accounts;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RefreshScope
@ComponentScan(basePackages = "com.raghav.microservices")
@EnableFeignClients
//@ComponentScans({@ComponentScan("com.raghav.microservices.accounts.controller")})
//@EnableJpaRepositories("com.raghav.microservices.accounts.repository")
//@EntityScan("com.raghav.microservices.accounts.model")
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
