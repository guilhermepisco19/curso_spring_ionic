package com.guilhermepisco.cursospring.domain.enums;

public enum PaymentStatus {

	PENDING(1, "Pending"),
	PAID(2, "PAid"),
	CANCELED(3, "Canceled");
	
	private int cod;
	private String description;
	
	private PaymentStatus(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static PaymentStatus toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		
		for (PaymentStatus paymentStatus : PaymentStatus.values()) {
			if(cod.equals(paymentStatus.getCod())){
				return paymentStatus;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
