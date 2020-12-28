package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosrey.springboot.backend.apirest.models.dao.IExpedienteDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Expediente;



@Service
public class ExpedienteServiceImpl implements IExpedienteService{

	@Autowired
	private IExpedienteDao ExpedienteDao;
	
	private Logger logger = LoggerFactory.getLogger(usuarioService.class);
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Expediente> findAll() {
		
		
		return (List<Expediente>) ExpedienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Expediente> findAll(Pageable pageable) {
		return ExpedienteDao.findAll(pageable);
	}
	
	
	@Override
	@Transactional(readOnly = true)	
	public Expediente findById(Long id) {
		// TODO Auto-generated method stub
		return ExpedienteDao.findById(id).orElse(null);
	}

	@Override	
	@Transactional
	public Expediente save(Expediente Expediente) {
		// TODO Auto-generated method stub
		return ExpedienteDao.save(Expediente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		ExpedienteDao.deleteById(id);
	}

	

}
