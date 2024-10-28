package com.example.javamvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class JavamvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavamvcApplication.class, args);
	}

}
