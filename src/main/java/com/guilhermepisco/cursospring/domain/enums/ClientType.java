package com.guilhermepisco.cursospring.domain.enums;

public enum ClientType {

	PHISICALPERSON(1, "Phisical Person"),
	LEGALPERSON(2, "Legal Person");
	
	private int cod;
	private String description;
	
	private ClientType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static ClientType toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		
		for (ClientType clientType : ClientType.values()) {
			if(cod.equals(clientType.getCod())){
				return clientType;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
	
	
}
