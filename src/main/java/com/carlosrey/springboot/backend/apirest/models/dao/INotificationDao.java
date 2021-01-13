package com.carlosrey.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.carlosrey.springboot.backend.apirest.models.entity.Notification;

public interface INotificationDao extends JpaRepository<Notification, Long>{

//	@Query("from Provincia")
//	public List<Provincia> findAllProvincias();
	
	public Notification findByTemplate(String templateCode);
	
	
}
