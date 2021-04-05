package com.carlosrey.springboot.backend.apirest.controllers;
/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Expediente;
import com.carlosrey.springboot.backend.apirest.models.entity.Mediador;
import com.carlosrey.springboot.backend.apirest.models.services.IExpedienteService;

/**
 * @author Carlos Rey Silva https://github.com/carlos1811
 */
/*
class ExpedienteRestControllerTest {

	
	private static final Long EXPEDIENTE_ID = 1L;
	private static final String NUMPOLIZA = "012391";
	private static final Mediador ASEGURADOR = new Mediador();
	private static final Cliente TOMADORSEGURO = new Cliente();
	private static final String AESEGURADORPROPIETARIO = "Propio";
	private static final Date FECHAINIPOLIZA = new Date(2020 - 02 - 01);
	private static final Date FECHAFINPOLIZA = new Date(2020 - 02 - 01);
	
	
	private static final String REVISION = "Carlos RD";
	private static final String TIPODEVIVIENDA = "Carlos RD";
	private static final String TIPODEREVISION = "anual";
	private static final String DIRECCION = "gaudi ";
	private static final String ANOCONSTRUCION = "2008";
	private static final String SUPERFICIEVIVIENDA = "73";
	private static final String ESTRUCTURA = "solida";
	private static final String EDIFICAVALORAREPOSICION = "1100";
	private static final String MOBILIARIOENSERES = "100";
	private static final String RCEDIFICACION = "100";
	private static final Boolean ACTIVO = true;
	
	
	
	
	public static final Expediente EXPEDIENTE = new Expediente();
	public static Expediente EXPEDIENTE_RESPONSE = new Expediente();
	public static List<Expediente> EXPEDIENTE_RESPONSE_LIST = new ArrayList<Expediente>();
	
	
	

	@Mock
	IExpedienteService iExpedienteService;

	@Mock
	private MessageSource messageSource;

	@InjectMocks
	ExpedienteRestController expedienteRestController;
	

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		EXPEDIENTE.setIdExpediente(EXPEDIENTE_ID);
		EXPEDIENTE.setNumPoliza(NUMPOLIZA);
		EXPEDIENTE.setAsegurador(ASEGURADOR);
		EXPEDIENTE.setTomadorSeguro(TOMADORSEGURO);
		EXPEDIENTE.setAseguPropietario(AESEGURADORPROPIETARIO);
		

		LocaleContextHolder.setLocale(Locale.US);

	}

	@Test
	@DisplayName("Test get provider by id")
	public void getExpedientesIdTest() {

		Mockito.when(iExpedienteService.findById(EXPEDIENTE_ID)).thenReturn(EXPEDIENTE);
		final ResponseEntity<?> response = expedienteRestController.findByExpediente(EXPEDIENTE_ID);

		EXPEDIENTE_RESPONSE = (Expediente) response.getBody();

		assertEquals(response.getStatusCode(), HttpStatus.OK);
		
		


	}

	@Test
	@DisplayName("Test get provider by id")
	public void getExpedientesIdTestError() {

		Mockito.when(iExpedienteService.findById(EXPEDIENTE_ID)).thenThrow(new DataAccessException("Error Junit") {
		});
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = expedienteRestController.findByExpediente(EXPEDIENTE_ID);

		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}

	@Test
	@DisplayName("Test get all provider")
	public void getExpedientesTest() {

		Mockito.when(iExpedienteService.findAll()).thenReturn(EXPEDIENTE_RESPONSE_LIST);
		final List<Expediente> response = expedienteRestController.findAll();

		assertEquals(response, EXPEDIENTE_RESPONSE_LIST);

	}

	@Test
	@DisplayName("Test create provider")
	public void createTest() {

		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(false);

		Mockito.when(iExpedienteService.save(EXPEDIENTE)).thenReturn(EXPEDIENTE);
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = expedienteRestController.create(EXPEDIENTE, result);

		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	@DisplayName("Test create provider validate error")
	public void createTestHasError() {

		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(true);

		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = expedienteRestController.create(EXPEDIENTE, result);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

	}


	@Test
	@DisplayName("Test create provider test error")
	public void createTestError() {

		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(false);

		Mockito.when(iExpedienteService.save(EXPEDIENTE)).thenThrow(new DataAccessException("Error Junit") {
		});
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = expedienteRestController.create(EXPEDIENTE, result);

		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}

	@Test
	@DisplayName("Test update provider")
	public void updateTest() {

		Mockito.when(iExpedienteService.findById(EXPEDIENTE_ID)).thenReturn(EXPEDIENTE);
		Mockito.when(iExpedienteService.save(EXPEDIENTE)).thenReturn(EXPEDIENTE);
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = expedienteRestController.update(EXPEDIENTE, EXPEDIENTE_ID);

		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	@DisplayName("Test update provider Error Dataaccess")
	public void updateTestError() {

		Mockito.when(iExpedienteService.findById(EXPEDIENTE_ID)).thenReturn(EXPEDIENTE);
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		Mockito.when(iExpedienteService.save(EXPEDIENTE)).thenThrow(new DataAccessException("Error Junit") {
		});

		final ResponseEntity<?> response = expedienteRestController.update(EXPEDIENTE, EXPEDIENTE_ID);

		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}

	@Test
	@DisplayName("Test delete provider")
	public void deleteTest() {

		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		doNothing().when(iExpedienteService).delete(EXPEDIENTE_ID);

		final ResponseEntity<?> response = expedienteRestController.delete(EXPEDIENTE_ID);

		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	@DisplayName("Test delete provider Error")
	public void deleteTestError() {

		 Mockito.when(messageSource.getMessage("controller.mensaje1", null,
		 LocaleContextHolder.getLocale())).thenReturn("Texto JUNIT");

		 doThrow(new DataAccessException("...") {}).when(iExpedienteService).delete(EXPEDIENTE_ID);

		 final ResponseEntity<?> response = expedienteRestController.delete(EXPEDIENTE_ID);

		 assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);

	}
	
}
*/