package com.carlosrey.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosrey.springboot.backend.apirest.models.dao.ISociedadDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Sociedad;

@Service
public class SociedadServiceImpl implements ISociedadService{

	@Autowired
	private ISociedadDao sociedadDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<Sociedad> findAll() {
		
		return (List<Sociedad>) sociedadDao.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)	
	public Sociedad findById(Long id) {
		// TODO Auto-generated method stub
		return sociedadDao.findById(id).orElse(null);
	}

	@Override	
	@Transactional
	public Sociedad save(Sociedad sociedad) {
		// TODO Auto-generated method stub
		return sociedadDao.save(sociedad);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		sociedadDao.deleteById(id);
	}

	

}
