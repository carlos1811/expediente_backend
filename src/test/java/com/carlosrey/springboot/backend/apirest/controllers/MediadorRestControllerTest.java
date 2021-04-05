package com.carlosrey.springboot.backend.apirest.controllers;

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

import com.carlosrey.springboot.backend.apirest.models.entity.Mediador;
import com.carlosrey.springboot.backend.apirest.models.services.IMediadorService;

/**
 * @author Carlos Rey Silva https://github.com/carlos1811
 */

class MediadorRestControllerTest {
	/*
	private static final Long MEDIADOR_ID = 1L;
	private static final String NAME = "carlos";
	private static final String APELLIDO = "rs";
	private static final Boolean ACTIVO = true;
	private static final Date CREATE_AT = new Date(2020 - 02 - 01);
	private static final String EMAIL = "MEDIADOR@gmail.com";
	
	
	public static final Mediador MEDIADOR = new Mediador();
	public static Mediador MEDIADOR_RESPONSE = new Mediador();
	public static List<Mediador> MEDIADOR_RESPONSE_LIST = new ArrayList<Mediador>();

	@Mock
	IMediadorService iMediadorService;

	@Mock
	private MessageSource messageSource;

	@InjectMocks
	MediadorRestController mediadorRestController;
	

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		MEDIADOR.setIdMediador(MEDIADOR_ID);
		MEDIADOR.setNombre(NAME);
		MEDIADOR.setEmail(EMAIL);
		MEDIADOR.setApellido(APELLIDO);
		MEDIADOR.setActivo(ACTIVO);
		MEDIADOR.setCreateAt(CREATE_AT);

		LocaleContextHolder.setLocale(Locale.US);

	}

	@Test
	@DisplayName("Test get provider by id")
	public void getMediadorsIdTest() {

		Mockito.when(iMediadorService.findById(MEDIADOR_ID)).thenReturn(MEDIADOR);
		final ResponseEntity<?> response = mediadorRestController.findByMediador(MEDIADOR_ID);

		MEDIADOR_RESPONSE = (Mediador) response.getBody();

		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(MEDIADOR_RESPONSE.getNombre(), NAME);
		assertEquals(MEDIADOR_RESPONSE.getEmail(), EMAIL);
		assertEquals(MEDIADOR_RESPONSE.getApellido(), APELLIDO);
		assertEquals(MEDIADOR_RESPONSE.getActivo(), true);
		assertEquals(MEDIADOR_RESPONSE.getCreateAt(), CREATE_AT);

	}

	@Test
	@DisplayName("Test get provider by id")
	public void getMediadorsIdTestError() {

		Mockito.when(iMediadorService.findById(MEDIADOR_ID)).thenThrow(new DataAccessException("Error Junit") {
		});
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = mediadorRestController.findByMediador(MEDIADOR_ID);

		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}

	@Test
	@DisplayName("Test get all provider")
	public void getMediadorsTest() {

		Mockito.when(iMediadorService.findAll()).thenReturn(MEDIADOR_RESPONSE_LIST);
		final List<Mediador> response = mediadorRestController.findAllMediador();

		assertEquals(response, MEDIADOR_RESPONSE_LIST);

	}

	@Test
	@DisplayName("Test create provider")
	public void createTest() {

		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(false);

		Mockito.when(iMediadorService.save(MEDIADOR)).thenReturn(MEDIADOR);
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = mediadorRestController.create(MEDIADOR, result);

		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	@DisplayName("Test create provider validate error")
	public void createTestHasError() {

		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(true);

		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = mediadorRestController.create(MEDIADOR, result);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

	}


	@Test
	@DisplayName("Test create provider test error")
	public void createTestError() {

		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(false);

		Mockito.when(iMediadorService.save(MEDIADOR)).thenThrow(new DataAccessException("Error Junit") {
		});
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = mediadorRestController.create(MEDIADOR, result);

		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}

	@Test
	@DisplayName("Test update provider")
	public void updateTest() {

		Mockito.when(iMediadorService.findById(MEDIADOR_ID)).thenReturn(MEDIADOR);
		Mockito.when(iMediadorService.save(MEDIADOR)).thenReturn(MEDIADOR);
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = mediadorRestController.update(MEDIADOR, MEDIADOR_ID);

		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	@DisplayName("Test update provider Error Dataaccess")
	public void updateTestError() {

		Mockito.when(iMediadorService.findById(MEDIADOR_ID)).thenReturn(MEDIADOR);
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		Mockito.when(iMediadorService.save(MEDIADOR)).thenThrow(new DataAccessException("Error Junit") {
		});

		final ResponseEntity<?> response = mediadorRestController.update(MEDIADOR, MEDIADOR_ID);

		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}

	@Test
	@DisplayName("Test delete provider")
	public void deleteTest() {

		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		doNothing().when(iMediadorService).delete(MEDIADOR_ID);

		final ResponseEntity<?> response = mediadorRestController.delete(MEDIADOR_ID);

		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	@DisplayName("Test delete provider Error")
	public void deleteTestError() {

		 Mockito.when(messageSource.getMessage("controller.mensaje1", null,
		 LocaleContextHolder.getLocale())).thenReturn("Texto JUNIT");

		 doThrow(new DataAccessException("...") {}).when(iMediadorService).delete(MEDIADOR_ID);

		 final ResponseEntity<?> response = mediadorRestController.delete(MEDIADOR_ID);

		 assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);

	}
*/
}

