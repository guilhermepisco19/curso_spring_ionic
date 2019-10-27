package com.guilhermepisco.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilhermepisco.cursospring.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
