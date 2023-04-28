package com.example.validaaation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableFeignClients
@Component("com.example.validaaation")
public class ValidaaationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidaaationApplication.class, args);
    }

}
