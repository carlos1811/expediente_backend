package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carlosrey.springboot.backend.apirest.models.entity.Mediador;
/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */
public interface IMediadorService {
	
	public List<Mediador> findAll();
	
	public Mediador findById(Long id);
	
	public Mediador save(Mediador mediador);
	
	public void delete(Long id);

	public Page<Mediador> findAll(Pageable pageable);

}
