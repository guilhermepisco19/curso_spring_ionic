package com.guilhermepisco.cursospring.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.guilhermepisco.cursospring.services.DBService;

@Configuration
@Profile("test")
public class testConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instatiateTestDatabase();
		return true;
	}
}
