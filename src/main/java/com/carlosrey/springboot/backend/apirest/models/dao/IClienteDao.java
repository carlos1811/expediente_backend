package com.carlosrey.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteDao extends	JpaRepository<Cliente, Long>{


	@Query("from Cliente c where c.activo = true ORDER BY id")
    List<Cliente> findByActiveTrue();
	
	
}
