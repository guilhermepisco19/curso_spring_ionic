package com.guilhermepisco.cursospring.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.guilhermepisco.cursospring.domain.Request;

public interface EmailService {

	void sendOrderConfirmationEmail(Request request);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Request request);
	
	void sendHtmlEmail(MimeMessage msg);
}
