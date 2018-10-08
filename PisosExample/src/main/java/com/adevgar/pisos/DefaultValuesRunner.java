package com.adevgar.pisos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.adevgar.pisos.service.AdvertServiceImpl;

@Component
public class DefaultValuesRunner implements CommandLineRunner {

	@Autowired
	AdvertServiceImpl pisos;

	public void run(String... args) throws Exception {
		
		pisos.defaultValues();
		
	}
	
	
	
}
