package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosrey.springboot.backend.apirest.models.dao.IClienteDao;
import com.carlosrey.springboot.backend.apirest.models.dao.INotificationDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Notificacion;
import com.carlosrey.springboot.backend.apirest.models.services.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private INotificationDao notificationDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		logger.info("inicio metodo findAll ");
		
		return clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAllCombo() {
		logger.info("inicio metodo findAllCombo ");
		
		return clienteDao.findByActiveTrue();
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		logger.info("inicio metodo pageable ");
		
		return clienteDao.findAll(pageable);
	}
	
	
	@Override
	@Transactional(readOnly = true)	
	public Cliente findById(Long id) {
		logger.info("inicio metodo findById ");
		return clienteDao.findById(id).orElse(null);
	}

	@Override	
	@Transactional
	public Cliente save(Cliente cliente) {
		logger.info("inicio metodo save ");
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		logger.info("inicio metodo delete ");
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Notificacion> findAllTemplates() {
		logger.info("inicio metodo delete ");
		
		return notificationDao.findAll();
	}



}
