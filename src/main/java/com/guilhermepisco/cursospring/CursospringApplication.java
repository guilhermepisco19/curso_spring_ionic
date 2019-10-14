package com.guilhermepisco.cursospring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guilhermepisco.cursospring.domain.Categoria;
import com.guilhermepisco.cursospring.domain.City;
import com.guilhermepisco.cursospring.domain.Product;
import com.guilhermepisco.cursospring.domain.State;
import com.guilhermepisco.cursospring.repositories.CategoriaRepository;
import com.guilhermepisco.cursospring.repositories.CityRepository;
import com.guilhermepisco.cursospring.repositories.ProductRepository;
import com.guilhermepisco.cursospring.repositories.StateRepository;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner{
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		

		Categoria cat1 = new Categoria(null, "Information Techonology");
		Categoria cat2 = new Categoria(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia",s1);
		City c2 = new City(null, "São Paulo",s2);
		City c3 = new City(null, "Campinas", s2);
		
		s1.getCities().addAll(Arrays.asList(c1));
		s2.getCities().addAll(Arrays.asList(c2,c3));
		
		stateRepository.saveAll(Arrays.asList(s1,s2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
	}

}
