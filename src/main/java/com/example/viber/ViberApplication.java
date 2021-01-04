package com.example.viber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.viber.*")
public class ViberApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViberApplication.class, args);
	}

}
