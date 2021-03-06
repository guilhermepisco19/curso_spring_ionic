package com.guilhermepisco.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermepisco.cursospring.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

	@Transactional(readOnly=true) /*As these is a SELECT query there is no need to create a transaction, so readOnly=true*/
	Client findByEmail(String email);
}
