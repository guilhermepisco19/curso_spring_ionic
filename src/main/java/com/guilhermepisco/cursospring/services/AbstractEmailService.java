package com.guilhermepisco.cursospring.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.guilhermepisco.cursospring.domain.Request;

public abstract class AbstractEmailService implements EmailService {
	
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
		sm.setSubject("Request comfirmed! COde: " + request.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(request.toString());
		return sm;
	}
}
