package com.guilhermepisco.cursospring.domain;

import javax.persistence.Entity;

import com.guilhermepisco.cursospring.domain.enums.PaymentStatus;

@Entity
public class PaymentWithCard extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer parcelsNumber;
	
	public PaymentWithCard() {
		
	}

	public PaymentWithCard(Integer id, PaymentStatus status, Request request, Integer parcelsNumber) {
		super(id, status, request);
		this.parcelsNumber = parcelsNumber;
	}

	public Integer getParcelsNumber() {
		return parcelsNumber;
	}

	public void setParcelsNumber(Integer parcelsNumber) {
		this.parcelsNumber = parcelsNumber;
	}
	
	
	
}
