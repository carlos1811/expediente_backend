package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosrey.springboot.backend.apirest.models.dao.ISociedadDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Sociedad;
import com.carlosrey.springboot.backend.apirest.models.services.ISociedadService;
/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */
@Service
public class SociedadServiceImpl implements ISociedadService{

	private static final Logger logger = LoggerFactory.getLogger(SociedadServiceImpl.class);
	
	@Autowired
	private ISociedadDao sociedadDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<Sociedad> findAll() {
		logger.info("inicio metodo findAll ");
		return (List<Sociedad>) sociedadDao.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)	
	public Sociedad findById(Long id) {
		logger.info("inicio metodo findById ");
		return sociedadDao.findById(id).orElse(null);
	}

	@Override	
	@Transactional
	public Sociedad save(Sociedad sociedad) {
		logger.info("inicio metodo save ");
		return sociedadDao.save(sociedad);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		logger.info("inicio metodo delete ");
		sociedadDao.deleteById(id);
	}

	

}
