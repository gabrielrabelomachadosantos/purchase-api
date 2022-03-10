package com.gabrielsantos.purchaseapi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableRabbit
@EnableFeignClients
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PurchaseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseApiApplication.class, args);
    }

}
