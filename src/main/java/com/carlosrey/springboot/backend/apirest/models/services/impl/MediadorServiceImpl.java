package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosrey.springboot.backend.apirest.models.dao.IMediadorDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Mediador;
import com.carlosrey.springboot.backend.apirest.models.services.IMediadorService;

/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */

@Service
public class MediadorServiceImpl implements IMediadorService{

	private static final Logger logger = LoggerFactory.getLogger(MediadorServiceImpl.class);
	
	@Autowired
	private IMediadorDao mediadorDao;
		
	@Override
	@Transactional(readOnly = true)
	public List<Mediador> findAll() {
		logger.info("inicio metodo findAll ");
		
		return (List<Mediador>) mediadorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Mediador> findAll(Pageable pageable) {
		logger.info("inicio metodo findAll ");
		return mediadorDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)	
	public Mediador findById(Long id) {
		logger.info("inicio metodo findById ");
		return mediadorDao.findById(id).orElse(null);
	}

	@Override	
	@Transactional
	public Mediador save(Mediador mediador) {
		logger.info("inicio metodo save ");
		return mediadorDao.save(mediador);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		logger.info("inicio metodo delete ");
		mediadorDao.deleteById(id);
	}

	

}
