package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlosrey.springboot.backend.apirest.models.dao.IClienteDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;
/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */
@Service
public class ProvinciaServiceImpl implements IProvinciaService{

	@Autowired
	private IClienteDao clienteDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ProvinciaServiceImpl.class);

	@Override
	public List<Provincia> findAllProvincias() {
		logger.info("inicio metodo findAllProvincias ");
		return clienteDao.findAllProvincias();
		
	}

	

}
