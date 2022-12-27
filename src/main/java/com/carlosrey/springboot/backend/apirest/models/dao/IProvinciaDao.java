package com.carlosrey.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;

public interface IProvinciaDao extends	JpaRepository<Provincia, Long>{

	@Query("from Provincia")
    List<Provincia> findAllProvincias();
	
}
