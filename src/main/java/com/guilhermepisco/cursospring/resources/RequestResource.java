package com.guilhermepisco.cursospring.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guilhermepisco.cursospring.domain.Request;
import com.guilhermepisco.cursospring.services.RequestService;

@RestController
@RequestMapping(value="/requests")
public class RequestResource {
	
	@Autowired
	private RequestService service;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Request> find(@PathVariable Integer id) {
		
		Request obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Request obj){
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Request>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, //24 its a good value because its multiple of 1,2,3
			@RequestParam(value="orderBy", defaultValue="instant") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		
		
		Page<Request> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	

}
