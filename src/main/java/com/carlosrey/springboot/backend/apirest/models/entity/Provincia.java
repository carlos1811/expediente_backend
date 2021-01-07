package com.carlosrey.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



@Entity
@Table(name="provincia")
public class Provincia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_provincia")
	private Long idProvincia;
	
	@Column(nullable = false)
	@Size(min=1 , max = 40)
	private String provincia;

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombre() {
		return provincia;
	}

	public void setNombre(String provincia) {
		this.provincia = provincia;
	}

	
	
	
	
}