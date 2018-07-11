package com.lrsilva.projetospring.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SMTPEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SMTPEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage obj) {
		LOG.info("Simulating sending email");
		mailSender.send(obj);

	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulating sending email");
		javaMailSender.send(msg);

	}

}
