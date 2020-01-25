package com.guilhermepisco.cursospring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermepisco.cursospring.domain.Request;
import com.guilhermepisco.cursospring.repositories.RequestRepository;
import com.guilhermepisco.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repo;
	
	public Request get(Integer id) {
		
		Optional<Request> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Request.class.getName()));
	}
}
