package com.carlosrey.springboot.backend.apirest.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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

import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;
import com.carlosrey.springboot.backend.apirest.models.services.IProvinciaService;

/**
 * @author Carlos Rey Silva https://github.com/carlos1811º
 */
/*
class ProvinciaRestControllerTest {

	private static final Long PROVINCIA_ID = 1L;
	private static final String NAME = "carlos";

	
	public static final Provincia PROVINCIA = new Provincia();
	public static Provincia PROVINCIA_RESPONSE = new Provincia();
	public static List<Provincia> PROVINCIA_RESPONSE_LIST = new ArrayList<Provincia>();

	@Mock
	IProvinciaService iProvinciaService;

	@Mock
	private MessageSource messageSource;

	@InjectMocks
	ProvinciaRestController provinciaRestController;
	

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		PROVINCIA.setIdProvincia(PROVINCIA_ID);
		PROVINCIA.setNombre(NAME);


		LocaleContextHolder.setLocale(Locale.US);

	}

	@Test
	@DisplayName("Test get all provider")
	public void getProvinciasTest() {

		Mockito.when(iProvinciaService.findAllProvincias()).thenReturn(PROVINCIA_RESPONSE_LIST);
		final List<Provincia> response = provinciaRestController.provincias();

		assertEquals(response, PROVINCIA_RESPONSE_LIST);

	}



}
*/