package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosrey.springboot.backend.apirest.models.dao.IExpedienteDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Expediente;
import com.carlosrey.springboot.backend.apirest.models.services.IExpedienteService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Carlos Rey Silva https://github.com/carlos1811
 */
@Slf4j
@Service
public class ExpedienteServiceImpl implements IExpedienteService {

	@Autowired
	private IExpedienteDao ExpedienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Expediente> findAll() {
		log.info("inicio metodo findAll ");

		return (List<Expediente>) ExpedienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Expediente> findAll(Pageable pageable) {
		log.info("inicio metodo findAll ");

		return ExpedienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Expediente findById(Long id) {
		log.info("inicio metodo findById ");
		return ExpedienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Expediente save(Expediente Expediente) {
		log.info("inicio metodo save ");
		return ExpedienteDao.save(Expediente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		log.info("inicio metodo delete ");
		ExpedienteDao.deleteById(id);
	}

}
