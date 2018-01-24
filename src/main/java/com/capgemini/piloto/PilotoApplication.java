package com.capgemini.piloto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class PilotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PilotoApplication.class, args);
	}
}
