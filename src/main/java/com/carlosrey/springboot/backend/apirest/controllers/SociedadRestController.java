package com.carlosrey.springboot.backend.apirest.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carlosrey.springboot.backend.apirest.models.entity.Sociedad;
import com.carlosrey.springboot.backend.apirest.models.services.ISociedadService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */

@Slf4j
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class SociedadRestController {


	@Autowired
	private ISociedadService sociedadService;

	@GetMapping("/sociedad")
	public  List<Sociedad> index() 
	
	{
		log.info("inicio metodo index ");
		List<Sociedad> sociedad = sociedadService.findAll();
		return sociedad;
	}
	
	@GetMapping("sociedad/{id}")
	public ResponseEntity<?> show(@PathVariable Long id)
	{
		log.info("inicio metodo show ");
		
		Sociedad sociedad = null;
		Map<String,Object> response = new HashMap<>();
		

		try {
			 sociedad = sociedadService.findById(id);
		} catch (Exception e) {
			response.put("mensaje", "El sociedad ID: ".concat(id.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Sociedad>(sociedad,HttpStatus.OK);
	}

	@PostMapping("sociedad")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Sociedad sociedad, BindingResult result)
	{
		
		log.info("inicio metodo create ");
		
		
		Sociedad sociedadNew = null;
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
			sociedadNew = sociedadService.save(sociedad);
		} catch (DataAccessException e) {
		//	response.put("mensaje", "El sociedad ID: ".concat(sociedad.getIdSociedad().toString().concat("no se pudo crear correctamente")));
			response.put("errors",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
	//	response.put("mensaje", "El sociedad ID: ".concat(sociedad.getIdSociedad().toString().concat("se creo correctamente")));
		response.put("sociedad", sociedadNew);
		
		return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}

	@PutMapping("sociedad/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Sociedad sociedad,@PathVariable Long id)
	{
		log.info("inicio metodo update ");
		
		Map<String,Object> response = new HashMap<>();
		
		try {
		
		Sociedad sociedadActual = sociedadService.findById(id);
		
		sociedadActual.setNombre(sociedad.getNombre());
		sociedadActual.setDireccion(sociedad.getDireccion());
		sociedadActual.setProvincia(sociedad.getProvincia());
		sociedadActual.setEmail(sociedad.getEmail());
		
		sociedadService.save(sociedad);
		
		} catch (DataAccessException e) {
	//		response.put("mensaje", "El sociedad ID: ".concat(sociedad.getIdSociedad().toString().concat("no existe en la base de datos!")));
			response.put("errors",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}


}
