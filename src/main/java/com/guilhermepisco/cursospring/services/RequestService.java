package com.guilhermepisco.cursospring.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermepisco.cursospring.domain.Client;
import com.guilhermepisco.cursospring.domain.PaymentWithBoleto;
import com.guilhermepisco.cursospring.domain.Request;
import com.guilhermepisco.cursospring.domain.RequestItem;
import com.guilhermepisco.cursospring.domain.enums.PaymentStatus;
import com.guilhermepisco.cursospring.repositories.ClientRepository;
import com.guilhermepisco.cursospring.repositories.PaymentRepository;
import com.guilhermepisco.cursospring.repositories.RequestItemRepository;
import com.guilhermepisco.cursospring.repositories.RequestRepository;
import com.guilhermepisco.cursospring.security.UserSS;
import com.guilhermepisco.cursospring.services.exceptions.AuthorizationException;
import com.guilhermepisco.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private RequestItemRepository requestItemRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Request find(Integer id) {
		
		Optional<Request> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Request.class.getName()));
	}
	
	@Transactional
	public Request insert(Request obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setStatus(PaymentStatus.PENDING);
		obj.getPayment().setRequest(obj);
		
		if(obj.getPayment() instanceof PaymentWithBoleto) {
			PaymentWithBoleto payment = (PaymentWithBoleto) obj.getPayment();
			boletoService.fillPaymentWithBoleto(payment,obj.getInstant());
			
		}
		
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		
		for(RequestItem item : obj.getItems()) {
			item.setDiscount(0.0);
			item.setProduct(productService.find(item.getProduct().getId()));
			item.setPrice(item.getProduct().getPrice());
			item.setRequest(obj);
		}
		
		requestItemRepository.saveAll(obj.getItems());

		emailService.sendOrderConfirmationHtmlEmail(obj);

		return obj;
	}
	
	public Page<Request> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		
		if(user == null) {
			throw new AuthorizationException("Access denied");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Client cli = clientService.find(user.getId());
		
		return repo.findByClient(cli, pageRequest);
	}
}
