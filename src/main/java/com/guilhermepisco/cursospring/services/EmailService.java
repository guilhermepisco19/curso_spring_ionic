package com.guilhermepisco.cursospring.services;

import org.springframework.mail.SimpleMailMessage;

import com.guilhermepisco.cursospring.domain.Request;

public interface EmailService {

	void sendOrderConfirmationEmail(Request request);
	
	void sendEmail(SimpleMailMessage msg);
}
