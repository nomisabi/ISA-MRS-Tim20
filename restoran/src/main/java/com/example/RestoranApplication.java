package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class RestoranApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestoranApplication.class, args);
	}

}
