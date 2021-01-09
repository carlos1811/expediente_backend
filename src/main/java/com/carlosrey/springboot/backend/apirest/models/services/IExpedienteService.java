package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carlosrey.springboot.backend.apirest.models.entity.Expediente;
/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */
public interface IExpedienteService {
	
	public List<Expediente> findAll();
	
	public Expediente findById(Long id);
	
	public Expediente save(Expediente Expediente);
	
	public void delete(Long id);

	public Page<Expediente> findAll(Pageable pageable);

}
