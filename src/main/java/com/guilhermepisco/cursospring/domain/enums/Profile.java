package com.guilhermepisco.cursospring.domain.enums;

public enum Profile {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT"); //Its is required by Spring Security to use the underline in the description
	
	private int cod;
	private String description;
	
	private Profile(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static Profile toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		
		for (Profile profile : Profile.values()) {
			if(cod.equals(profile.getCod())){
				return profile;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
