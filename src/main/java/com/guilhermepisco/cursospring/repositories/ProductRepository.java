package com.guilhermepisco.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilhermepisco.cursospring.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
