package com.guilhermepisco.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilhermepisco.cursospring.domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{

}
