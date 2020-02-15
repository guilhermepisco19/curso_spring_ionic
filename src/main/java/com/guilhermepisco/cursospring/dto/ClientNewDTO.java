package com.guilhermepisco.cursospring.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.guilhermepisco.cursospring.services.validations.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Mandatory filling")
	@Length(min=5, max=120, message="Size must be between 5 and 120 characters")	
	private String name;
	
	@NotEmpty(message="Mandatory filling")
	@Email(message="Invalid email")
	private String email;
	
	@NotEmpty(message="Mandatory filling")
	private String cpfOrCnpj;
	
	private Integer clientType;
	
	@NotEmpty(message="Mandatory filling")
	private String publicPlaces;
	
	@NotEmpty(message="Mandatory filling")
	private String number;
	
	private String complement;
	private String neighborhood;
	
	@NotEmpty(message="Mandatory filling")
	private String cep;
	
	@NotEmpty(message="Mandatory filling")
	private String phone;
	
	private String phone2;
	private String phone3;
	
	private Integer cityId;
	
	public ClientNewDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getPublicPlaces() {
		return publicPlaces;
	}

	public void setPublicPlaces(String publicPlaces) {
		this.publicPlaces = publicPlaces;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
}
