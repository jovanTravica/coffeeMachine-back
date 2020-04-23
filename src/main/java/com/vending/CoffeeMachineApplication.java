package com.vending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class })

@ComponentScan(basePackages ={"com.vending"})
public class CoffeeMachineApplication {

	public static void main(String[] args) {

		SpringApplication.run(CoffeeMachineApplication.class, args);
	}

}
