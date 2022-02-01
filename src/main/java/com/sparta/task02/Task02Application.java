package com.sparta.task02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Task02Application {

	public static void main(String[] args) {
		SpringApplication.run(Task02Application.class, args);
	}

}
