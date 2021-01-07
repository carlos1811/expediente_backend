package com.carlosrey.springboot.backend.apirest.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.services.IClienteService;

class ClienteRestControllerTest {

	private static final Long CLIENTE_ID = 1L;
	private static final String NAME = "carlos";
	private static final String EMAIL = "carlos@gmail.com";
	
	
	public static final Cliente CLIENTE = new Cliente();
	
	
	@Mock
	IClienteService iClienteService;
	
	
	@InjectMocks
	ClienteRestController clienteRestController;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
				
		CLIENTE.setNombre(NAME);
		CLIENTE.setEmail(EMAIL);
				
	}
	
	

	@Test
	void getClientesIdTest() {
		final ResponseEntity<?> response = clienteRestController.getClientesId(CLIENTE_ID);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	
				
		
	}


}
