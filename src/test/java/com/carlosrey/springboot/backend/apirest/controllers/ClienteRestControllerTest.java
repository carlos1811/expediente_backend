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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.carlosrey.springboot.backend.apirest.configuration.Config;
import com.carlosrey.springboot.backend.apirest.json.EmailCliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Notificacion;
import com.carlosrey.springboot.backend.apirest.models.services.IClienteService;
import com.carlosrey.springboot.backend.apirest.models.services.IEmailService;

/**
 * @author Carlos Rey Silva https://github.com/carlos1811
 */

class ClienteRestControllerTest {

	private static final Long CLIENTE_ID = 1L;
	private static final String NAME = "carlos";
	private static final String APELLIDO = "rs";
	private static final Boolean ACTIVO = true;
	private static final Date CREATE_AT = new Date(2020 - 02 - 01);
	private static final String EMAIL = "carlos@gmail.com";
	
	
	private static final Long TEMPLATE_ID = 1L;
	private static final String TEMPLATE = "<html></html>";
	private static final String TEMPLATETYPE = "PAYMENT";
	

	public static final Cliente CLIENTE = new Cliente();
	public static final EmailCliente EMAILCLIENTE = new EmailCliente();
	
	public static final Notificacion TEMPLATEID = new Notificacion();
	public static Cliente CLIENTE_RESPONSE = new Cliente();
	public static List<Cliente> CLIENTE_RESPONSE_LIST = new ArrayList<Cliente>();
	public static List<Notificacion> TEMPLATES_RESPONSE_LIST = new ArrayList<Notificacion>();

	

	@Mock
	IClienteService iClienteService;

	@Mock
	private MessageSource messageSource;
	
	@Mock
	private IEmailService iEmailService;
	
	@InjectMocks
	ClienteRestController clienteRestController;
	
//	@Mock
//	JasperFillManager jasperFillManager;
	
	@Autowired
	private Config config;
	

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		CLIENTE.setIdCliente(CLIENTE_ID);
		CLIENTE.setNombre(NAME);
		CLIENTE.setEmail(EMAIL);
		CLIENTE.setApellido(APELLIDO);
		CLIENTE.setActivo(ACTIVO);
		CLIENTE.setCreateAt(CREATE_AT);
		
		EMAILCLIENTE.setIdCliente(CLIENTE);
		EMAILCLIENTE.setNombreCliente(NAME);
		EMAILCLIENTE.setTemplateType(TEMPLATETYPE);
		
		
		TEMPLATEID.setId(TEMPLATE_ID);
		TEMPLATEID.setTemplate(TEMPLATE);
		TEMPLATEID.setTemplateType(TEMPLATETYPE);

		LocaleContextHolder.setLocale(Locale.US);

	}

	@Test
	@DisplayName("Test get customer by id")
	public void getClientesIdTest() {

		Mockito.when(iClienteService.findById(CLIENTE_ID)).thenReturn(CLIENTE);
		final ResponseEntity<?> response = clienteRestController.getClientesId(CLIENTE_ID);

		CLIENTE_RESPONSE = (Cliente) response.getBody();

		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(CLIENTE_RESPONSE.getNombre(), NAME);
		assertEquals(CLIENTE_RESPONSE.getEmail(), EMAIL);
		assertEquals(CLIENTE_RESPONSE.getApellido(), APELLIDO);
		assertEquals(CLIENTE_RESPONSE.getActivo(), true);
		assertEquals(CLIENTE_RESPONSE.getCreateAt(), CREATE_AT);

	}

	@Test
	@DisplayName("Test get customer by id")
	public void getClientesIdTestError() {

		Mockito.when(iClienteService.findById(CLIENTE_ID)).thenThrow(new DataAccessException("Error Junit") {
		});
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = clienteRestController.getClientesId(CLIENTE_ID);

		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}

	@Test
	@DisplayName("Test get all customer")
	public void getClientesTest() {

		Mockito.when(iClienteService.findAll()).thenReturn(CLIENTE_RESPONSE_LIST);
		final List<Cliente> response = clienteRestController.getClientes();

		assertEquals(response, CLIENTE_RESPONSE_LIST);

	}
	
	
	@Test
	@DisplayName("Test get all templates")
	public void getTemplatesTest() {

		Mockito.when(iClienteService.findAllTemplates()).thenReturn(TEMPLATES_RESPONSE_LIST);
		final List<Notificacion> response = clienteRestController.templatesAll();

		assertEquals(response, TEMPLATES_RESPONSE_LIST);

	}
	
	@Test
	@DisplayName("send Email Test")
	public void sendEmailTest() throws Exception{
		
		Mockito.when(iClienteService.findById(CLIENTE.getIdCliente())).thenReturn(CLIENTE);
		
		doNothing().when(iEmailService).processSendEmail(CLIENTE.getEmail(), "FACTURA", CLIENTE.getNombre());
		
		final ResponseEntity<?> response = clienteRestController.sendEmail(EMAILCLIENTE);
		 
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		
	}
	
	
	@Test
	@DisplayName("send Email Error")
	public void sendEmailError() throws Exception{
		
		//Mockito.when(iClienteService.findById(CLIENTE.getIdCliente())).thenReturn(CLIENTE);
		
		Mockito.when(iClienteService.findById(CLIENTE_ID)).thenThrow(new DataAccessException("Error Junit") {
		});

	//	Mockito.doThrow(new Exception("es un error") {}).when(iEmailService).processSendEmail(CLIENTE.getEmail(), "FACTURA", CLIENTE.getNombre());
		
		final ResponseEntity<?> response = clienteRestController.sendEmail(EMAILCLIENTE);
		 
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
		
	}
	
	
	@Test
	@DisplayName("send Email Null")
	public void sendEmailErrorMailNull() throws Exception{
		
		CLIENTE.setEmail(null);
		
		Mockito.when(iClienteService.findById(CLIENTE.getIdCliente())).thenReturn(CLIENTE);

		 doThrow(new Exception("...") {}).when(iEmailService).processSendEmail(CLIENTE.getEmail(), "FACTURA", CLIENTE.getNombre());
		
		final ResponseEntity<?> response = clienteRestController.sendEmail(EMAILCLIENTE);
		 
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
		
	}
	
	
	
	

	@Test
	@DisplayName("Test create customer")
	public void createTest() {

		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(false);

		Mockito.when(iClienteService.save(CLIENTE)).thenReturn(CLIENTE);
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = clienteRestController.create(CLIENTE, result);

		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

		
	
	
	@Test
	@DisplayName("Test create customer validate error")
	public void createTestHasError() {

		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(true);

		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = clienteRestController.create(CLIENTE, result);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

	/**
	 * @author Carlos Rey Silva https://github.com/carlos1811
	 */

	@Test
	@DisplayName("Test create customer test error")
	public void createTestError() {

		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(false);

		Mockito.when(iClienteService.save(CLIENTE)).thenThrow(new DataAccessException("Error Junit") {
		});
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = clienteRestController.create(CLIENTE, result);

		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}

	@Test
	@DisplayName("Test update customer")
	public void updateTest() {

		Mockito.when(iClienteService.findById(CLIENTE_ID)).thenReturn(CLIENTE);
		Mockito.when(iClienteService.save(CLIENTE)).thenReturn(CLIENTE);
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		final ResponseEntity<?> response = clienteRestController.update(CLIENTE, CLIENTE_ID);

		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	@DisplayName("Test update customer Error Dataaccess")
	public void updateTestError() {

		Mockito.when(iClienteService.findById(CLIENTE_ID)).thenReturn(CLIENTE);
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		Mockito.when(iClienteService.save(CLIENTE)).thenThrow(new DataAccessException("Error Junit") {
		});

		final ResponseEntity<?> response = clienteRestController.update(CLIENTE, CLIENTE_ID);

		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}

	@Test
	@DisplayName("Test delete customer")
	public void deleteTest() {

		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
				.thenReturn("Texto JUNIT");

		doNothing().when(iClienteService).delete(CLIENTE_ID);

		final ResponseEntity<?> response = clienteRestController.delete(CLIENTE_ID);

		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	@DisplayName("Test delete customer Error")
	public void deleteTestError() {

		 Mockito.when(messageSource.getMessage("controller.mensaje1", null,
		 LocaleContextHolder.getLocale())).thenReturn("Texto JUNIT");

		 doThrow(new DataAccessException("...") {}).when(iClienteService).delete(CLIENTE_ID);

		 final ResponseEntity<?> response = clienteRestController.delete(CLIENTE_ID);

		 assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);

	}
	
	/*
	@Test
	@DisplayName("Test report customer Error")
	public void reportTestError() throws JRException, ClassNotFoundException, SQLException{
		
		
		String pathReport = "C:/Users/carlos pc/JaspersoftWorkspace/MyReports/Invoice.jrxml";
		Map<String, Object> parameters = new HashMap<>();
		
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
		.thenReturn("Texto JUNIT");
		
		Mockito.when(jasperFillManager.fillReport(pathReport,parameters,config.getDataSource().getConnection())).thenThrow(new Exception("Error Junit") {
		});
		

		final ResponseEntity<?> response = clienteRestController.report();
		
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		
	}
	
	
	@Test
	@DisplayName("Test report customer ")
	public void reportTest() throws JRException, ClassNotFoundException, SQLException{
		
		
		String pathReport = "C:/Users/carlos pc/JaspersoftWorkspace/MyReports/Invoice.jrxml";
		Map<String, Object> parameters = new HashMap<>();
		
		Mockito.when(messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()))
		.thenReturn("Texto JUNIT");
		
		Mockito.when(jasperFillManager.fillReport(pathReport,parameters,config.getDataSource().getConnection())).thenThrow(new Exception("Error Junit") {
		});
		

		final ResponseEntity<?> response = clienteRestController.report();
		
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		
	}
	
	*/
	

}
