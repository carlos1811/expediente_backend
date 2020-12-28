package com.carlosrey.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Mediador;

public interface IMediadorDao extends JpaRepository<Mediador, Long>{

}
