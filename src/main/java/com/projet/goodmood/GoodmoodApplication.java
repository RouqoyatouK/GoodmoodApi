package com.projet.goodmood;

import com.projet.goodmood.controller.AuthController;
import com.projet.goodmood.models.ERole;
import com.projet.goodmood.models.Role;
import com.projet.goodmood.payload.request.SignupRequest;
import com.projet.goodmood.repository.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashSet;
import java.util.Set;

//@EnableJpaRepositories
//@ComponentScan(basePackages = {"@ComponentScan(basePackages = {"com.projet.goodmood.service.CitationSrv"})"})
@SpringBootApplication
@EnableScheduling
public class GoodmoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodmoodApplication.class, args);
	}

	@Bean
	CommandLineRunner start(RoleRepo roleRepository, AuthController authController ) {

		return args -> {

			if(roleRepository.findByName(ERole.ROLE_ADMIN)== null) roleRepository.save(new Role(ERole.ROLE_ADMIN));
			if(roleRepository.findByName(ERole.ROLE_USER)== null) roleRepository.save(new Role(ERole.ROLE_USER));

			Set<String> roles = new HashSet<>();
			roles.add("adminn");
			SignupRequest defaultuser = new SignupRequest();
			defaultuser.setUsername("adminn");
			defaultuser.setEmail("adminn@gmail.com");
			defaultuser.setPassword("123456789");
			defaultuser.setRole(roles);
			authController.registerUser(defaultuser);



		};
	}

}
