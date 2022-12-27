package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carlosrey.springboot.backend.apirest.models.entity.Mediador;

public interface IMediadorService {
	
	List<Mediador> findAll();
	
	Mediador findById(Long id);
	
	Mediador save(Mediador mediador);
	
	void delete(Long id);

	Page<Mediador> findAll(Pageable pageable);

}
