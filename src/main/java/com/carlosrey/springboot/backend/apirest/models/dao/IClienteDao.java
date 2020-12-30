package com.carlosrey.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;

public interface IClienteDao extends	JpaRepository<Cliente, Long>{

	@Query("from Provincia")
	public List<Provincia> findAllProvincias();
	
}
