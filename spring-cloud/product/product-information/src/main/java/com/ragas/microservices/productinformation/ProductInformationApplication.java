package com.ragas.microservices.productinformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class ProductInformationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductInformationApplication.class, args);
	}
}
