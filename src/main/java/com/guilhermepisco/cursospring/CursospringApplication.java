package com.guilhermepisco.cursospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guilhermepisco.cursospring.services.S3Service;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner{
	
	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("C:\\Users\\ASUS-PC\\Pictures\\SSL-TLS Handshaking.jpg");
	}

}
