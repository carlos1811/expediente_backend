package com.carlosrey.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosrey.springboot.backend.apirest.models.entity.Sociedad;

public interface ISociedadDao extends JpaRepository<Sociedad, Long>{

}
