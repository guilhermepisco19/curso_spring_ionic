package com.guilhermepisco.cursospring.services;

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

import com.guilhermepisco.cursospring.domain.Request;

public abstract class AbstractEmailService implements EmailService {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Request request) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromRequest(request);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromRequest(Request request) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(request.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Request comfirmed! Code: " + request.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(request.toString());
		return sm;
	}
	
	protected String htmlFromRequestTemplate(Request obj) {
		Context context = new Context();
		
		context.setVariable("request", obj);
		
		return templateEngine.process("email/requestConfirmation", context);
		
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Request request) {
		try {
			MimeMessage mm = prepareMimeMessageFromRequest(request);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(request);
		}
	}

	protected MimeMessage prepareMimeMessageFromRequest(Request request) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(request.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Request confirmed! Code: " + request.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromRequestTemplate(request), true);
		return mimeMessage;
	}
}
