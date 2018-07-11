package com.lrsilva.projetospring.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage obj) {
		LOG.info("Simulating sending email");
		LOG.info(obj.toString());
		LOG.info("Email sent");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulating sending html email");
		LOG.info(msg.toString());
		LOG.info("Email Sent");

	}

}
