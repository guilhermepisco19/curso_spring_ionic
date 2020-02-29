package com.guilhermepisco.cursospring.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermepisco.cursospring.domain.Categoria;
import com.guilhermepisco.cursospring.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Transactional(readOnly=true) /*As these is a SELECT query there is no need to create a transaction, so readOnly=true*/
	Page<Product> findDistictByNameContainingAndCategoriasIn(String name, List<Categoria> categorias, Pageable pageRequest);
	/***** 
	 * SAME AS
	 * @Query("SELECT DISTINCT obj "
	 + "FROM Product obj "
	 + "INNER JOIN obj.categorias cat "
	 + "WHERE obj.name LIKE %:name% AND cat IN :categorias")
	Page<Product> search(@Param("name") String name, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);******/
}
