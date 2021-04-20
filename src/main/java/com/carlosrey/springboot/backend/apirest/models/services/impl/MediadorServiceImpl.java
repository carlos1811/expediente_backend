package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosrey.springboot.backend.apirest.models.dao.IMediadorDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Mediador;
import com.carlosrey.springboot.backend.apirest.models.services.IMediadorService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */
@Slf4j
@Service
public class MediadorServiceImpl implements IMediadorService{

	@Autowired
	private IMediadorDao mediadorDao;
		
	@Override
	@Transactional(readOnly = true)
	public List<Mediador> findAll() {
		log.info("inicio metodo findAll ");
		
		return (List<Mediador>) mediadorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Mediador> findAll(Pageable pageable) {
		log.info("inicio metodo findAll ");
		return mediadorDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)	
	public Mediador findById(Long id) {
		log.info("inicio metodo findById ");
		return mediadorDao.findById(id).orElse(null);
	}

	@Override	
	@Transactional
	public Mediador save(Mediador mediador) {
		log.info("inicio metodo save ");
		return mediadorDao.save(mediador);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		log.info("inicio metodo delete ");
		mediadorDao.deleteById(id);
	}

	

}
