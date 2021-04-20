package com.carlosrey.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;
import com.carlosrey.springboot.backend.apirest.models.services.IProvinciaService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */

@Slf4j
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProvinciaRestController {

	@Autowired
	private IProvinciaService provinciaService;

	@GetMapping("/clientes/provincia")
	public List<Provincia> provincias()

	{
		log.info("inicio metodo index ");
		List<Provincia> provincias = provinciaService.findAllProvincias();
		return provincias;
	}

}
