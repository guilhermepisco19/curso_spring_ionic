package com.guilhermepisco.cursospring.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guilhermepisco.cursospring.domain.enums.PaymentStatus;

@Entity
public class PaymentWithBoleto extends Payment{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date expiratioDate;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date paymentDate;
	
	public PaymentWithBoleto() {
		
	}

	public PaymentWithBoleto(Integer id, PaymentStatus status, Request request, Date expirationDate, Date paymentDate) {
		super(id, status, request);
		this.expiratioDate = expirationDate;
		this.paymentDate = paymentDate;
	}

	public Date getExpiratioDate() {
		return expiratioDate;
	}

	public void setExpiratioDate(Date expiratioDate) {
		this.expiratioDate = expiratioDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	

}
