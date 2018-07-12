package com.lrsilva.projetospring.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.lrsilva.projetospring.domain.Client;
import com.lrsilva.projetospring.domain.OrderT;

public interface EmailService {

	void sendOrderConfirmationEmail(OrderT obj);

	void sendEmail(SimpleMailMessage obj);

	void sendOrderConfirmationHtmlEmail(OrderT obj);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Client client, String newPass);
}
