package com.guilhermepisco.cursospring.services.validations;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.guilhermepisco.cursospring.domain.Client;
import com.guilhermepisco.cursospring.domain.enums.ClientType;
import com.guilhermepisco.cursospring.dto.ClientNewDTO;
import com.guilhermepisco.cursospring.repositories.ClientRepository;
import com.guilhermepisco.cursospring.services.exceptions.FieldMessage;
import com.guilhermepisco.cursospring.services.validations.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getName() == null) {
			list.add(new FieldMessage("name", "Name can´t be NULL"));
		}
		
		if(objDTO.getClientType().equals(ClientType.PHISICALPERSON.getCod()) && !BR.isValidCPF(objDTO.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF!"));
		}
		
		if(objDTO.getClientType().equals(ClientType.LEGALPERSON.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ!"));
		}
		
		Client aux = clientRepo.findByEmail(objDTO.getEmail());
		
		if(aux != null) {
			list.add(new FieldMessage("email", "Email already exists!"));
		}
		
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}