package com.carlosrey.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */

@Setter
@Getter
@Entity
@Table(name="expediente")
public class Expediente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_expediente")
	private Long idExpediente;
	
	@Size(min=1 , max = 30)
	private String numPoliza;
	
	//Mediador
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_mediador")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Mediador asegurador;
	
	//Cliente
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_cliente")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Cliente tomadorSeguro;

    
	//Cliente
	private String aseguPropietario;
		
	//Cliente
	private Date fechaIniPoliza;
	
	private Date fechaFinPoliza;
	
	private String revision;
	
	private String tipoVivienda;
	
	private String tipoRevision;
	
	private String direccion;
	
	private Integer anoConstrucion;
	
	private Integer superficieVivienda;
	
	private String estructura;
	
	private Integer edificaValoraReposicion;
	
	private Integer mobiliarioEnseres;
	
	private Integer rcEdificacion;
	
	private Boolean activo;
	
}
