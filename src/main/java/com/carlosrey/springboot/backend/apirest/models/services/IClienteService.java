package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Notificacion;

public interface IClienteService {
	
	List<Cliente> findAll();
	
	List<Cliente> findAllCombo();
	
	Cliente findById(Long id);
	
	Cliente save(Cliente cliente);
	
	void delete(Long id);

	Page<Cliente> findAll(Pageable pageable);
	
	List<Notificacion> findAllTemplates();

	
}
