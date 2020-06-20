package com.guilhermepisco.cursospring.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermepisco.cursospring.domain.Client;
import com.guilhermepisco.cursospring.domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{

	@Transactional(readOnly=true)
	Page<Request> findByClient(Client client, Pageable pageableRequest);
}
