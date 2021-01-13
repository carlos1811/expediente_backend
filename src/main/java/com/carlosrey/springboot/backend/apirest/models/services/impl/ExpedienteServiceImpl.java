package com.carlosrey.springboot.backend.apirest.models.services.impl;

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
import com.carlosrey.springboot.backend.apirest.models.services.IExpedienteService;

/**
 * @author Carlos Rey Silva https://github.com/carlos1811
 */

@Service
public class ExpedienteServiceImpl implements IExpedienteService {

	@Autowired
	private IExpedienteDao ExpedienteDao;

	private static final Logger logger = LoggerFactory.getLogger(ExpedienteServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public List<Expediente> findAll() {
		logger.info("inicio metodo findAll ");

		return (List<Expediente>) ExpedienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Expediente> findAll(Pageable pageable) {
		logger.info("inicio metodo findAll ");

		return ExpedienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Expediente findById(Long id) {
		logger.info("inicio metodo findById ");
		return ExpedienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Expediente save(Expediente Expediente) {
		logger.info("inicio metodo save ");
		return ExpedienteDao.save(Expediente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		logger.info("inicio metodo delete ");
		ExpedienteDao.deleteById(id);
	}

}
