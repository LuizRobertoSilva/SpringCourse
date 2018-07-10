package com.lrsilva.projetospring.services;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SMTPEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SMTPEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage obj) {
		LOG.info("Simulando envio de email");
		mailSender.send(obj);

	}

}
