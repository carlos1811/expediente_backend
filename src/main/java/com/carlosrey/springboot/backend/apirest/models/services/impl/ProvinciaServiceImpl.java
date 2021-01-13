package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlosrey.springboot.backend.apirest.models.dao.IProvinciaDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;
import com.carlosrey.springboot.backend.apirest.models.services.IProvinciaService;
/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */
@Service
public class ProvinciaServiceImpl implements IProvinciaService{

	@Autowired
	private IProvinciaDao provinciaDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ProvinciaServiceImpl.class);

	@Override
	public List<Provincia> findAllProvincias() {
		logger.info("inicio metodo findAllProvincias ");
		return provinciaDao.findAllProvincias();
		
	}

	

}
