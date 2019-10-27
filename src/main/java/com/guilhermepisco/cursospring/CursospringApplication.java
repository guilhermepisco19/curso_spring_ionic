package com.guilhermepisco.cursospring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guilhermepisco.cursospring.domain.Address;
import com.guilhermepisco.cursospring.domain.Categoria;
import com.guilhermepisco.cursospring.domain.City;
import com.guilhermepisco.cursospring.domain.Client;
import com.guilhermepisco.cursospring.domain.Product;
import com.guilhermepisco.cursospring.domain.State;
import com.guilhermepisco.cursospring.domain.enums.ClientType;
import com.guilhermepisco.cursospring.repositories.AddressRepository;
import com.guilhermepisco.cursospring.repositories.CategoriaRepository;
import com.guilhermepisco.cursospring.repositories.CityRepository;
import com.guilhermepisco.cursospring.repositories.ClientRepository;
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
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	
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
		
		
		Client cli1 = new Client(null, "Maria SIlva", "maria@gmail.com", "36378912377", ClientType.PHISICALPERSON);
		cli1.getPhone().addAll(Arrays.asList("27363323","93838393"));
		
		Address ad1 = new Address(null, "Rua FLores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Address ad2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(ad1,ad2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(ad1,ad2));
	}

}
