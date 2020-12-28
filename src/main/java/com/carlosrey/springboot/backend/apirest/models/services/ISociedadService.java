package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import com.carlosrey.springboot.backend.apirest.models.entity.Sociedad;

public interface ISociedadService {
	
	public List<Sociedad> findAll();
	
	public Sociedad findById(Long id);
	
	public Sociedad save(Sociedad sociedad);
	
	public void delete(Long id);



}
