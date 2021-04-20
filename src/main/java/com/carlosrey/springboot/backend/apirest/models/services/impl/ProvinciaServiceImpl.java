package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlosrey.springboot.backend.apirest.models.dao.IProvinciaDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;
import com.carlosrey.springboot.backend.apirest.models.services.IProvinciaService;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */
@Slf4j
@Service
public class ProvinciaServiceImpl implements IProvinciaService{

	@Autowired
	private IProvinciaDao provinciaDao;
	
	@Override
	public List<Provincia> findAllProvincias() {
		log.info("inicio metodo findAllProvincias ");
		return provinciaDao.findAllProvincias();
		
	}

	

}
