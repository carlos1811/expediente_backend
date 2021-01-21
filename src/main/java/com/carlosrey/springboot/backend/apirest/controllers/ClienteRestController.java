package com.carlosrey.springboot.backend.apirest.controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carlosrey.springboot.backend.apirest.configuration.Config;
import com.carlosrey.springboot.backend.apirest.json.EmailCliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Notificacion;
import com.carlosrey.springboot.backend.apirest.models.services.IClienteService;
import com.carlosrey.springboot.backend.apirest.models.services.IEmailService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private Config config;
	
	@Autowired
	private IEmailService iEmailService;

	@Autowired
	private MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(ClienteRestController.class);

	@GetMapping("/clientes/combo")
	public List<Cliente> getClientesCombo()

	{
		logger.info("inicio metodo getClientes ");
		List<Cliente> cliente = clienteService.findAllCombo();
		return cliente;
	}
	
	@GetMapping("/clientes")
	public List<Cliente> getClientes()

	{
		logger.info("inicio metodo getClientes ");
		List<Cliente> cliente = clienteService.findAll();
		return cliente;
	}
	

	@GetMapping("clientes/{id}")
	public ResponseEntity<?> getClientesId(@PathVariable Long id) {
		logger.info("inicio metodo getClientesId ");

		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = clienteService.findById(id);
		} catch (Exception e) {

			String mensajeException = messageSource.getMessage("controller.mensaje1", null,LocaleContextHolder.getLocale()) + id.toString()
					+ messageSource.getMessage("controller.mensaje2", null, LocaleContextHolder.getLocale());

			response.put("mensaje", mensajeException);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}


	//No se usa en front, se mantiene el codigo por si hubiera un front con paginacion
	/*
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page)

	{
		logger.info("inicio metodo index Paginacion ");
		Page<Cliente> cliente = clienteService.findAll(PageRequest.of(page, 4));
		return cliente;
	}*/

	@PostMapping("clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {

		logger.info("inicio metodo create ");

		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> " El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			
			String mensaje = messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale())
					+ cliente.getIdCliente().toString()
					+ messageSource.getMessage("controller.mensaje4", null, LocaleContextHolder.getLocale());
			
			response.put("mensaje", mensaje);
			response.put("errors", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		String mensaje = messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale())
				+ cliente.getIdCliente().toString()
				+ messageSource.getMessage("controller.mensaje3", null, LocaleContextHolder.getLocale());

		response.put("cliente", clienteNew);
		response.put("mensaje", mensaje);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
		logger.info("inicio metodo update ");

		Map<String, Object> response = new HashMap<>();

		try {

			Cliente clienteActual = clienteService.findById(id);

			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setFechaPrueba(cliente.getFechaPrueba());
			clienteActual.setProvincia(cliente.getProvincia());

			clienteService.save(cliente);

		} catch (DataAccessException e) {
			
			String mensaje = messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale())
					+ cliente.getIdCliente().toString()
					+ messageSource.getMessage("controller.mensaje5", null, LocaleContextHolder.getLocale());
			
			response.put("mensaje", mensaje );
			response.put("errors", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", messageSource.getMessage("controller.mensaje6", null, LocaleContextHolder.getLocale()) );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@DeleteMapping("clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		logger.info("inicio metodo delete ");

		Map<String, Object> response = new HashMap<>();

		try {

			clienteService.delete(id);

		} catch (DataAccessException e) {
			
			String mensaje = messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale())
					+ id.toString()
					+ messageSource.getMessage("controller.mensaje5", null, LocaleContextHolder.getLocale());
			
			response.put("mensaje", mensaje);
			response.put("errors", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		String mensaje = messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale())
				+ id.toString()
				+ messageSource.getMessage("controller.mensaje7", null, LocaleContextHolder.getLocale());		
		
		response.put("mensaje", mensaje);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	
	@GetMapping("/clientes/exportar")
	public ResponseEntity<?> report() throws JRException, ClassNotFoundException, SQLException

	{
		logger.info("inicio metodo report ");

		Map<String, Object> response = new HashMap<>();

		String pathReport = "C:/Users/carlos pc/JaspersoftWorkspace/MyReports/Invoice.jrxml";

		try {
			// Compilar el fichero jrxml
			JasperReport report = JasperCompileManager.compileReport(pathReport);

			// Rellenamos los parámetros del informe con valores
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("the_image", "src/main/files/the_image.png");

			// rellenamos el informe con datos y parámetros
			JasperPrint print = JasperFillManager.fillReport(report, parameters,
					config.getDataSource().getConnection());

			String pathDestiny = "C:/Users/carlos pc/JaspersoftWorkspace/MyReports/Invoice" + LocalDate.now() + ".pdf";

			JasperExportManager.exportReportToPdfFile(print, pathDestiny);

		} catch (Exception e) {

			response.put("mensaje", messageSource.getMessage("controller.mensaje1", null, LocaleContextHolder.getLocale()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
	
		response.put("mensaje",messageSource.getMessage("controller.mensaje10", null, LocaleContextHolder.getLocale()));

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@PostMapping("email")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> sendEmail(@RequestBody EmailCliente emailCliente) {

		logger.info("inicio metodo sendEmail ");
		
		Map<String, Object> response = new HashMap<>();

		try {
			
			Cliente cliente = clienteService.findById(emailCliente.getIdCliente().getIdCliente());
			
			if (cliente.getEmail() != null) {
				
				String nombreCompleto = cliente.getNombre() + ' ' + cliente.getApellido();
			
			iEmailService.processSendEmail(cliente.getEmail(), emailCliente.getTemplateType(), nombreCompleto);
			 
			}
			else {
				
				String mensaje = "no esta informado el email para este cliente: "
						+ cliente.getIdCliente().toString();
				response.put("mensaje", mensaje);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			
			String mensaje = "Se ha producido un error en el envio de email";
			
			response.put("mensaje", mensaje);
			response.put("errors", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		String mensaje = "Email enviado correctamente";

		response.put("mensaje", mensaje);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	
	
	
	@GetMapping("/clientes/templates")
	public List<Notificacion> templatesAll()

	{
		logger.info("inicio metodo templatesAll ");
		List<Notificacion> notificaciones = clienteService.findAllTemplates();
		
		System.out.print(notificaciones);
		
		return notificaciones;
	}

}
