package com.projet.goodmood;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories
//@ComponentScan(basePackages = {"@ComponentScan(basePackages = {"com.projet.goodmood.service.CitationSrv"})"})
@SpringBootApplication
public class GoodmoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodmoodApplication.class, args);
	}

}
