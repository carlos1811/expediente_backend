package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosrey.springboot.backend.apirest.models.dao.ISociedadDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Sociedad;
import com.carlosrey.springboot.backend.apirest.models.services.ISociedadService;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */
@Slf4j
@Service
public class SociedadServiceImpl implements ISociedadService{

	@Autowired
	private ISociedadDao sociedadDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<Sociedad> findAll() {
		log.info("inicio metodo findAll ");
		return (List<Sociedad>) sociedadDao.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)	
	public Sociedad findById(Long id) {
		log.info("inicio metodo findById ");
		return sociedadDao.findById(id).orElse(null);
	}

	@Override	
	@Transactional
	public Sociedad save(Sociedad sociedad) {
		log.info("inicio metodo save ");
		return sociedadDao.save(sociedad);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		log.info("inicio metodo delete ");
		sociedadDao.deleteById(id);
	}

	

}
