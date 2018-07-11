package com.lrsilva.projetospring.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lrsilva.projetospring.domain.OrderT;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(OrderT obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(obj);
		sendEmail(sm);
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(OrderT obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromOrder(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException ex) {
			sendOrderConfirmationEmail(obj);
		}

	}

	protected MimeMessage prepareMimeMessageFromOrder(OrderT obj) throws MessagingException {
		MimeMessage mm = javaMailSender.createMimeMessage();
		MimeMessageHelper mh = new MimeMessageHelper(mm, true);

		mh.setTo(obj.getClient().getEmail());
		mh.setFrom(sender);
		mh.setSubject("Order confirmed! Order number: " + obj.getId());
		mh.setSentDate(new Date(System.currentTimeMillis()));
		mh.setText(htmlFromTemplateOrder(obj), true);

		return mm;
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(OrderT obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order confirmed!.. Order Number: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

	protected String htmlFromTemplateOrder(OrderT obj) {
		Context context = new Context();
		context.setVariable("order", obj);
		return templateEngine.process("email/OrderConfimation", context);

	}

}
