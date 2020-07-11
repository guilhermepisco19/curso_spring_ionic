package com.guilhermepisco.cursospring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermepisco.cursospring.domain.State;
import com.guilhermepisco.cursospring.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository repo;
	
	public List<State> findAll() {
		return repo.findAllByOrderByName();
	}
	
}
