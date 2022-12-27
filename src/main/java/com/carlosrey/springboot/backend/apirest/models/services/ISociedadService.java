package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import com.carlosrey.springboot.backend.apirest.models.entity.Sociedad;

public interface ISociedadService {
	
	List<Sociedad> findAll();
	
	Sociedad findById(Long id);
	
	Sociedad save(Sociedad sociedad);
	
	void delete(Long id);

}
