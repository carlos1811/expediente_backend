package com.carlosrey.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carlosrey.springboot.backend.apirest.models.entity.Notificacion;

public interface INotificationDao extends JpaRepository<Notificacion, Long>{

	
	Notificacion findByTemplate(String templateType);
	
	
    @Query("from Notificacion n where n.templateType = ?1")
    Notificacion findByTemplateType(String TemplateType);
	
	
	@Query("from Notificacion ORDER BY id")
    List<Notificacion> findAll();
	
	
	
	
	
}
