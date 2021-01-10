package com.carlosrey.springboot.backend.apirest.controllers;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.carlosrey.springboot.backend.apirest.models.entity.Mediador;
import com.carlosrey.springboot.backend.apirest.models.services.IMediadorService;

import javassist.bytecode.stackmap.TypeData.ClassName;
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
public class MediadorRestController {

	@Autowired
	private IMediadorService mediadorService;

	@Autowired
	private MessageSource messageSource;
    
    @Autowired
    private Config config;
	
    private static final Logger logger = LoggerFactory.getLogger(MediadorRestController.class);

	@GetMapping("/mediador")
	public  List<Mediador> findAllMediador() 
	
	{
		logger.info("inicio metodo index ");
		List<Mediador> mediador = mediadorService.findAll();
		return mediador;
	}
	
	
	
	/* Se comenta por si se utilizara en el futuro
	@GetMapping("/mediador/page/{page}")
	public  Page<Mediador> index(@PathVariable Integer page) 
	
	{
		logger.info("inicio metodo index Paginacion ");
		Page<Mediador> mediador = mediadorService.findAll(PageRequest.of(page,4));
		return mediador;
	}
	*/
	
	
	@GetMapping("mediador/{id}")
	public ResponseEntity<?> findByMediador(@PathVariable Long id)
	{
		logger.info("inicio metodo show ");
		
		Mediador mediador = null;
		Map<String,Object> response = new HashMap<>();
		

		try {
			 mediador = mediadorService.findById(id);
		} catch (Exception e) {
			
			
			String mensajeException = messageSource.getMessage("controller.mensaje8", null,
					Locale.getDefault()) + id.toString()
					+ messageSource.getMessage("controller.mensaje2", null, Locale.getDefault());
			
			
			response.put("mensaje", mensajeException);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Mediador>(mediador,HttpStatus.OK);
	}
	
	@PostMapping("mediador")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Mediador mediador, BindingResult result)
	{
		
		logger.info("inicio metodo create ");
		
		
		Mediador mediadorNew = null;
		Map<String,Object> response = new HashMap<>();
		
		if (result.hasErrors()){

			List<String> errors = result.getFieldErrors().stream().
					map( err -> 
					" El campo '" + err.getField() + "' " + err.getDefaultMessage()).
					collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}	
			
		try {
			mediadorNew = mediadorService.save(mediador);
		} catch (DataAccessException e) {
			
			
			String mensajeException = messageSource.getMessage("controller.mensaje8", null,
					Locale.getDefault()) + mediador.toString()
					+ messageSource.getMessage("controller.mensaje4", null, Locale.getDefault());
			
			response.put("mensaje", mensajeException);
			response.put("errors",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		String mensajeException = messageSource.getMessage("controller.mensaje8", null,
				Locale.getDefault()) + mediador.toString()
				+ messageSource.getMessage("controller.mensaje3", null, Locale.getDefault());
		
		response.put("mensaje", mensajeException);
		response.put("mediador", mediadorNew);
		
		return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}

	@PutMapping("mediador/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Mediador mediador,@PathVariable Long id)
	{
		logger.info("inicio metodo update ");
		
		Map<String,Object> response = new HashMap<>();
		
		try {
		
		Mediador mediadorActual = mediadorService.findById(id);
		
		mediadorActual.setNombre(mediador.getNombre());
		mediadorActual.setEmail(mediador.getEmail());
		mediadorActual.setCodMediador(mediador.getCodMediador());
		mediadorActual.setTelefono(mediador.getTelefono());
		mediadorActual.setActivo(mediador.getActivo());

		mediadorService.save(mediador);
		
		} catch (DataAccessException e) {
			
			String mensajeException = messageSource.getMessage("controller.mensaje8", null,
					Locale.getDefault()) + id.toString()
					+ messageSource.getMessage("controller.mensaje5", null, Locale.getDefault());
			
			response.put("mensaje", mensajeException);
			response.put("errors",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", messageSource.getMessage("controller.mensaje6", null,
				Locale.getDefault()));
		
		return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("mediador/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		logger.info("inicio metodo delete ");
		
		Map<String,Object> response = new HashMap<>();
		
		try {
		
		mediadorService.delete(id);
		
		} catch (DataAccessException e) {
		
			String mensajeException = messageSource.getMessage("controller.mensaje8", null,
					Locale.getDefault()) + id.toString()
					+ messageSource.getMessage("controller.mensaje5", null, Locale.getDefault());
			
			response.put("mensaje", mensajeException);
			response.put("errors",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
	
		String mensajeException = messageSource.getMessage("controller.mensaje8", null,
				Locale.getDefault()) + id.toString()
				+ messageSource.getMessage("controller.mensaje7", null, Locale.getDefault());
			
		response.put("mensaje", mensajeException);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	

	
}
