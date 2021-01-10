package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;
/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */
public interface IProvinciaService {
	
	public List<Provincia> findAllProvincias();

}
