package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class})
public class TwoDsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwoDsDemoApplication.class, args);
	}

}
