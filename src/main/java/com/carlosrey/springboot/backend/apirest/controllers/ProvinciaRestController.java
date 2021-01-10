package com.carlosrey.springboot.backend.apirest.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;
import com.carlosrey.springboot.backend.apirest.models.services.IProvinciaService;

/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProvinciaRestController {

	@Autowired
	private IProvinciaService provinciaService;

	private static final Logger logger = LoggerFactory.getLogger(ProvinciaRestController.class);


	@GetMapping("/clientes/provincia")
	public List<Provincia> provincias()

	{
		logger.info("inicio metodo index ");
		List<Provincia> provincias = provinciaService.findAllProvincias();
		return provincias;
	}

}
