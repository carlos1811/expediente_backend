package com.carlosrey.springboot.backend.apirest.models.services;

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

/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */

@Service
public class MediadorServiceImpl implements IMediadorService{

	@Autowired
	private IMediadorDao mediadorDao;
	
	private Logger logger = LoggerFactory.getLogger(usuarioService.class);
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Mediador> findAll() {
		
		
		return (List<Mediador>) mediadorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Mediador> findAll(Pageable pageable) {
		return mediadorDao.findAll(pageable);
	}
	
	
	@Override
	@Transactional(readOnly = true)	
	public Mediador findById(Long id) {
		// TODO Auto-generated method stub
		return mediadorDao.findById(id).orElse(null);
	}

	@Override	
	@Transactional
	public Mediador save(Mediador mediador) {
		// TODO Auto-generated method stub
		return mediadorDao.save(mediador);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		mediadorDao.deleteById(id);
	}

	

}
