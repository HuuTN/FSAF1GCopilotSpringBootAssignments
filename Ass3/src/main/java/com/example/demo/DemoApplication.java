package com.example.demo;

import com.example.demo.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(UserRepository userRepository) {
		return args -> {
			userRepository.save(new UserDTO(null, "Nguyen Van A", "a@example.com"));
			userRepository.save(new UserDTO(null, "Tran Thi B", "b@example.com"));
			userRepository.save(new UserDTO(null, "Le Van C", "c@example.com"));
		};
	}
}
