package com.example.javamvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
public class JavamvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavamvcApplication.class, args);
	}

}
