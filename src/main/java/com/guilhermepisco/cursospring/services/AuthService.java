package com.guilhermepisco.cursospring.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.guilhermepisco.cursospring.domain.Client;
import com.guilhermepisco.cursospring.repositories.ClientRepository;
import com.guilhermepisco.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Client cli = clientRepository.findByEmail(email);
		
		if(cli == null) {
			throw new ObjectNotFoundException("Email not found");
		}
		
		String newPass = newPassword();
		
		cli.setPassword(pe.encode(newPass));
		
		clientRepository.save(cli);
		
		emailService.sendNewPasswordEmail(cli, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10]; 
		
		for(int i = 0; i<10; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		
		if(opt == 0) { // generate a digit
			return (char) (rand.nextInt(10) + 48);
		}
		else if(opt == 1) { // generate upper case
			return (char) (rand.nextInt(26) + 65);
		}
		else { // generate lower case
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
