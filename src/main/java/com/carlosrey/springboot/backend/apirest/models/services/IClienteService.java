package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);

	public Page<Cliente> findAll(Pageable pageable);
	
	public List<Provincia> findAllProvincias();

}
