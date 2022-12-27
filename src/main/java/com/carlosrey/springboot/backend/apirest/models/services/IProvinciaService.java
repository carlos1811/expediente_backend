package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;

public interface IProvinciaService {
	
	List<Provincia> findAllProvincias();

}
