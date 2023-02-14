package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ApiProductsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiProductsApplication.class, args);
	}

}
