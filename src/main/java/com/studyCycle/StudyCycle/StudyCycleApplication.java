package com.studyCycle.StudyCycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication


public class StudyCycleApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyCycleApplication.class, args);
	}

}
