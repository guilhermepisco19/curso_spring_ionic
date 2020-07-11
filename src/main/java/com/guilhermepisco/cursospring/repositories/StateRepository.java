package com.guilhermepisco.cursospring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermepisco.cursospring.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

	@Transactional(readOnly=true)
	public List<State> findAllByOrderByName();
	
}
