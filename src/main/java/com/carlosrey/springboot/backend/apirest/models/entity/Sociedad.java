package com.carlosrey.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name="sociedad")
public class Sociedad implements Serializable {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_sociedad")
	private Long idSociedad;
	
	
	@Column(nullable = false)
	@NotEmpty
	@Size(min=1 , max = 20)
	private String nombre;
	
	@Size(min=1 , max = 10)
	private String nif;
	
	@Column(nullable = true)
	@Size(min=1 , max = 50)
	private String direccion;
	
	@Column(nullable = true)
	@Size(min=1 , max = 50)
	private String provincia;
	
	@Column(nullable = true)
	@Email
	private String email;
	
	
	private static final long serialVersionUID = 1L;
}
