package com.example.viber;

import com.example.viber.model.Receiver;
import com.example.viber.repository.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.example.viber.*")
public class ViberApplication implements CommandLineRunner {

	@Autowired
	ReceiverRepository receiverRepository;

	public static void main(String[] args) {
		SpringApplication.run(ViberApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		Receiver receiver = new Receiver("2", "Olaf", "sdgfh@msfs");
		receiverRepository.save(receiver);
	}
}
