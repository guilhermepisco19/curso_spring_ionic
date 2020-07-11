package com.guilhermepisco.cursospring.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermepisco.cursospring.domain.City;
import com.guilhermepisco.cursospring.domain.State;
import com.guilhermepisco.cursospring.dto.CityDTO;
import com.guilhermepisco.cursospring.dto.StateDTO;
import com.guilhermepisco.cursospring.services.CityService;
import com.guilhermepisco.cursospring.services.StateService;
import com.guilhermepisco.cursospring.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value="/states")
public class StateResource {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll(){
		List<State> list = stateService.findAll();
		List<StateDTO> listDto = list.stream().map(StateDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}/cities", method=RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCitiesByState(@PathVariable Integer id){
		List<City> list = cityService.findAllByStateId(id);
		List<CityDTO> listDTO = list.stream().map(CityDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
