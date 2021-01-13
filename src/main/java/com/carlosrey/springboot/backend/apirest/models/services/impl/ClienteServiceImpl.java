package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosrey.springboot.backend.apirest.controllers.ClienteRestController;
import com.carlosrey.springboot.backend.apirest.models.dao.IClienteDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;
import com.carlosrey.springboot.backend.apirest.models.services.IClienteService;
/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */
@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		logger.info("inicio metodo findAll ");
		
		return (List<Cliente>) clienteDao.findAll();
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


/*	@Override
	public List<Provincia> findAllProvincias() {
		logger.info("inicio metodo findAllProvincias ");
		return clienteDao.findAllProvincias();
		
	}
*/

}
