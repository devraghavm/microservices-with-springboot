package com.raghav.microservices.hello.world.service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    @GetMapping
    public String hello() {
        return "Welcome to Spring Boot Microservices";
    }
}
