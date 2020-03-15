package com.guilhermepisco.cursospring.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.guilhermepisco.cursospring.domain.Address;
import com.guilhermepisco.cursospring.domain.Categoria;
import com.guilhermepisco.cursospring.domain.City;
import com.guilhermepisco.cursospring.domain.Client;
import com.guilhermepisco.cursospring.domain.Payment;
import com.guilhermepisco.cursospring.domain.PaymentWithBoleto;
import com.guilhermepisco.cursospring.domain.PaymentWithCard;
import com.guilhermepisco.cursospring.domain.Product;
import com.guilhermepisco.cursospring.domain.Request;
import com.guilhermepisco.cursospring.domain.RequestItem;
import com.guilhermepisco.cursospring.domain.State;
import com.guilhermepisco.cursospring.domain.enums.ClientType;
import com.guilhermepisco.cursospring.domain.enums.PaymentStatus;
import com.guilhermepisco.cursospring.domain.enums.Profile;
import com.guilhermepisco.cursospring.repositories.AddressRepository;
import com.guilhermepisco.cursospring.repositories.CategoriaRepository;
import com.guilhermepisco.cursospring.repositories.CityRepository;
import com.guilhermepisco.cursospring.repositories.ClientRepository;
import com.guilhermepisco.cursospring.repositories.PaymentRepository;
import com.guilhermepisco.cursospring.repositories.ProductRepository;
import com.guilhermepisco.cursospring.repositories.RequestItemRepository;
import com.guilhermepisco.cursospring.repositories.RequestRepository;
import com.guilhermepisco.cursospring.repositories.StateRepository;

@Service
public class DBService {
	
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
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private RequestItemRepository requestItemRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiateTestDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Information Techonology");
		Categoria cat2 = new Categoria(null, "Office");
		Categoria cat3 = new Categoria(null, "Bed table and bath");
		Categoria cat4 = new Categoria(null, "Electronics");
		Categoria cat5 = new Categoria(null, "Gardening");
		Categoria cat6 = new Categoria(null, "Decoration");
		Categoria cat7 = new Categoria(null, "Perfumery");
		
		
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Office table", 300.00);
		Product p5 = new Product(null, "Towel", 50.00);
		Product p6 = new Product(null, "Quilt", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Brushcutter", 800.00);
		Product p9 = new Product(null, "Lampshade", 100.00);
		Product p10 = new Product(null, "Dangling", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5,p6));
		cat4.getProducts().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9,p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6, cat7));
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia",s1);
		City c2 = new City(null, "São Paulo",s2);
		City c3 = new City(null, "Campinas", s2);
		
		s1.getCities().addAll(Arrays.asList(c1));
		s2.getCities().addAll(Arrays.asList(c2,c3));
		
		stateRepository.saveAll(Arrays.asList(s1,s2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Client cli1 = new Client(null, "Maria SIlva", "guilhermepisco19@gmail.com", "36378912377", ClientType.PHISICALPERSON, pe.encode("123"));
		cli1.getPhone().addAll(Arrays.asList("27363323","93838393"));
		
		Client cli2 = new Client(null, "Ana Costa", "g.pisco@campus.fct.unl.pt", "19240156763", ClientType.PHISICALPERSON, pe.encode("123"));
		cli2.getPhone().addAll(Arrays.asList("9346625","346685596"));
		cli2.addProfile(Profile.ADMIN);
		
		
		Address ad1 = new Address(null, "Rua FLores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Address ad2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Address ad3 = new Address(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(ad1,ad2));
		cli2.getAddresses().addAll(Arrays.asList(ad3));
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		addressRepository.saveAll(Arrays.asList(ad1, ad2, ad3));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Request req1 = new Request(null,sdf.parse("30/09/2017 10:32"), cli1, ad1);
		Request req2 = new Request(null,sdf.parse("10/10/2017 19:35"), cli1, ad2);
		
		Payment pay1 = new PaymentWithCard(null, PaymentStatus.PAID, req1, 6);
		req1.setPayment(pay1);
		
		Payment pay2 = new PaymentWithBoleto(null, PaymentStatus.PENDING, req2,sdf.parse("20/10/2017 00:00"),null);
		req2.setPayment(pay2);
		
		cli1.getRequests().addAll(Arrays.asList(req1,req2));
		
		requestRepository.saveAll(Arrays.asList(req1,req2));
		paymentRepository.saveAll(Arrays.asList(pay1,pay2));
		
		RequestItem reqItem1 = new RequestItem(req1, p1, 0.00, 1, 2000.00);
		RequestItem reqItem2 = new RequestItem(req1, p3, 0.00, 2, 80.00);
		RequestItem reqItem3 = new RequestItem(req2, p2, 100.00, 1, 800.00);
		
		req1.getItems().addAll(Arrays.asList(reqItem1,reqItem2));
		req2.getItems().addAll(Arrays.asList(reqItem3));
		
		p1.getItems().addAll(Arrays.asList(reqItem1));
		p2.getItems().addAll(Arrays.asList(reqItem3));
		p3.getItems().addAll(Arrays.asList(reqItem2));
		
		requestItemRepository.saveAll(Arrays.asList(reqItem1,reqItem2,reqItem3));
	}
}
