package com.example.firstapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import com.example.firstapp.entities.Voyage;
import com.example.firstapp.repositories.VoyageRepository;
import java.util.Date;
import com.example.firstapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.firstapp.entities.MyUser;
import com.example.firstapp.services.UserService;
import java.util.Arrays;

@SpringBootApplication
public class FirstappApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(FirstappApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	

	@Bean
	CommandLineRunner testDatabase(UserRepository userRepository) {
		return args -> {
			try {
				long count = userRepository.count();
				System.out.println("Nombre d'utilisateurs dans la base : " + count);
			} catch (Exception e) {
				System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
				e.printStackTrace();
			}
		};
	}

	@Bean
	CommandLineRunner initUsers(UserService userService) {
		return args -> {
			try {
				userService.createAdminIfNotExists();
				System.out.println("Configuration des utilisateurs terminée");
			} catch (Exception e) {
				System.err.println("Erreur lors de l'initialisation des utilisateurs : " + e.getMessage());
			}
		};
	}

}
