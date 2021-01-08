package com.carlosrey.springboot.backend.apirest.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.services.IClienteService;

class ClienteRestControllerTest {

	private static final Long CLIENTE_ID = 1L;
	private static final String NAME = "carlos";
	private static final String APELLIDO = "rs";
	private static final Boolean ACTIVO = true;
	private static final Date CREATE_AT = new Date(2020-02-01);
	private static final String EMAIL = "carlos@gmail.com";
	
	public static final Cliente CLIENTE = new Cliente();
	public static Cliente CLIENTE_RESPONSE = new Cliente();
	public static List<Cliente> CLIENTE_RESPONSE_LIST = new ArrayList<Cliente>();
	
	
	@Mock
	IClienteService iClienteService;
	
	@InjectMocks
	ClienteRestController clienteRestController;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
				
		CLIENTE.setIdCliente(CLIENTE_ID);
		CLIENTE.setNombre(NAME);
		CLIENTE.setEmail(EMAIL);
		CLIENTE.setApellido(APELLIDO);
		CLIENTE.setActivo(ACTIVO);
		CLIENTE.setCreateAt(CREATE_AT);
			
	}
	

	@Test
	public void getClientesIdTest() {
		
		Mockito.when(iClienteService.findById(CLIENTE_ID)).thenReturn(CLIENTE);
		final ResponseEntity<?> response = clienteRestController.getClientesId(CLIENTE_ID);
		
		CLIENTE_RESPONSE  =	(Cliente) response.getBody();	
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(CLIENTE_RESPONSE.getNombre(),NAME);
		assertEquals(CLIENTE_RESPONSE.getEmail(),EMAIL);
		assertEquals(CLIENTE_RESPONSE.getApellido(),APELLIDO);
		assertEquals(CLIENTE_RESPONSE.getActivo(),true);
		assertEquals(CLIENTE_RESPONSE.getCreateAt(),CREATE_AT);
		
	}
	
	@Test
	public void getClientesTest() {
		
		Mockito.when(iClienteService.findAll()).thenReturn(CLIENTE_RESPONSE_LIST);
		final List<Cliente> response = clienteRestController.getClientes();
		

		assertEquals(response, CLIENTE_RESPONSE_LIST);
		
	}
	
	
	@Test
	public void createTest() {
		
		Mockito.when(iClienteService.save(CLIENTE)).thenReturn(CLIENTE);
		
	    BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(false);
		
		final ResponseEntity<?> response  = clienteRestController.create(CLIENTE, result);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		
	}
	
	@Test
	public void updateTest() {
		
		Mockito.when(iClienteService.findById(CLIENTE_ID)).thenReturn(CLIENTE);
		Mockito.when(iClienteService.save(CLIENTE)).thenReturn(CLIENTE);
		
		final ResponseEntity<?> response  = clienteRestController.update(CLIENTE, CLIENTE_ID);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);

		
	}
	
	@Test
	public void deleteTest() {
		
		Mockito.when(iClienteService.findById(CLIENTE_ID)).thenReturn(CLIENTE);
		Mockito.when(iClienteService.save(CLIENTE)).thenReturn(CLIENTE);
		
		doNothing().when(iClienteService).delete(CLIENTE_ID);
		
		final ResponseEntity<?> response  = clienteRestController.delete(CLIENTE_ID);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);

		
	}


	
	
	
	
	

}
