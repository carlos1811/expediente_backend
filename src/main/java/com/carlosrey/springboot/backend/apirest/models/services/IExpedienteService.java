package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carlosrey.springboot.backend.apirest.models.entity.Expediente;

public interface IExpedienteService {
	
	List<Expediente> findAll();
	
	Expediente findById(Long id);
	
	Expediente save(Expediente Expediente);
	
	void delete(Long id);

	Page<Expediente> findAll(Pageable pageable);

}
