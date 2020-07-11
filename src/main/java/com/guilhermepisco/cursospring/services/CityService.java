package com.guilhermepisco.cursospring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermepisco.cursospring.domain.City;
import com.guilhermepisco.cursospring.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;
	
	public List<City> findAllByStateId(Integer id) {
		
		return repo.findAllByStateIdOrderByName(id);
		
	}
}
