package com.guilhermepisco.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilhermepisco.cursospring.domain.RequestItem;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Integer>{

}
