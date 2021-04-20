package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;

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

import lombok.extern.slf4j.Slf4j;
/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */
@Slf4j
@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private INotificationDao notificationDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		log.info("inicio metodo findAll ");
		
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAllCombo() {
		log.info("inicio metodo findAllCombo ");
		
		return (List<Cliente>) clienteDao.findByActiveTrue();
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		log.info("inicio metodo pageable ");
		
		return clienteDao.findAll(pageable);
	}
	
	
	@Override
	@Transactional(readOnly = true)	
	public Cliente findById(Long id) {
		log.info("inicio metodo findById ");
		return clienteDao.findById(id).orElse(null);
	}

	@Override	
	@Transactional
	public Cliente save(Cliente cliente) {
		log.info("inicio metodo save ");
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		log.info("inicio metodo delete ");
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Notificacion> findAllTemplates() {
		log.info("inicio metodo delete ");
		
		return (List<Notificacion>) notificationDao.findAll();
	}



}
