package com.lrsilva.projetospring.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lrsilva.projetospring.services.DBService;
import com.lrsilva.projetospring.services.EmailService;
import com.lrsilva.projetospring.services.SMTPEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDataBase() throws ParseException {

		if (!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiateDataBase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new SMTPEmailService();
	}

}
