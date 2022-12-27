package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class ExpedienteServiceImpl implements IExpedienteService {

	@Autowired
	private IExpedienteDao expedienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Expediente> findAll() {
		log.info("inicio metodo findAll ");

		return expedienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Expediente> findAll(Pageable pageable) {
		log.info("inicio metodo findAll ");

		return expedienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Expediente findById(Long id) {
		log.info("inicio metodo findById ");
		return expedienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Expediente save(Expediente expediente) {
		log.info("inicio metodo save ");
		return expedienteDao.save(expediente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		log.info("inicio metodo delete ");
		expedienteDao.deleteById(id);
	}
}
