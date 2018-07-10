package com.lrsilva.projetospring.services;

import org.springframework.mail.SimpleMailMessage;

import com.lrsilva.projetospring.domain.OrderT;

public interface EmailService {

	void sendOrderConfirmationEmail(OrderT obj);
	
	void sendEmail(SimpleMailMessage obj);
}
