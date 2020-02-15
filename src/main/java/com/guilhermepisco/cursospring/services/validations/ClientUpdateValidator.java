package com.guilhermepisco.cursospring.services.validations;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.guilhermepisco.cursospring.domain.Client;
import com.guilhermepisco.cursospring.domain.enums.ClientType;
import com.guilhermepisco.cursospring.dto.ClientDTO;
import com.guilhermepisco.cursospring.repositories.ClientRepository;
import com.guilhermepisco.cursospring.services.exceptions.FieldMessage;
import com.guilhermepisco.cursospring.services.validations.utils.BR;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientDTO objDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getName() == null) {
			list.add(new FieldMessage("name", "Name can´t be NULL"));
		}
		
		Client aux = clientRepo.findByEmail(objDTO.getEmail());
		
		if(aux != null && !aux.getId().equals(uriId)) {
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