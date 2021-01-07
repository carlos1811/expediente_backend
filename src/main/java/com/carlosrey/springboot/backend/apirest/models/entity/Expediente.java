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

	
	
	
	
	public Long getIdExpediente() {
		return idExpediente;
	}

	public void setIdExpediente(Long idExpediente) {
		this.idExpediente = idExpediente;
	}

	public String getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}

	public String getAseguPropietario() {
		return aseguPropietario;
	}

	public void setAseguPropietario(String aseguPropietario) {
		this.aseguPropietario = aseguPropietario;
	}

	public Date getFechaIniPoliza() {
		return fechaIniPoliza;
	}

	public void setFechaIniPoliza(Date fechaIniPoliza) {
		this.fechaIniPoliza = fechaIniPoliza;
	}

	public Date getFechaFinPoliza() {
		return fechaFinPoliza;
	}

	public void setFechaFinPoliza(Date fechaFinPoliza) {
		this.fechaFinPoliza = fechaFinPoliza;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getTipoVivienda() {
		return tipoVivienda;
	}

	public void setTipoVivienda(String tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}

	public String getTipoRevision() {
		return tipoRevision;
	}

	public void setTipoRevision(String tipoRevision) {
		this.tipoRevision = tipoRevision;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getAnoConstrucion() {
		return anoConstrucion;
	}

	public void setAnoConstrucion(Integer anoConstrucion) {
		this.anoConstrucion = anoConstrucion;
	}

	public Integer getSuperficieVivienda() {
		return superficieVivienda;
	}

	public void setSuperficieVivienda(Integer superficieVivienda) {
		this.superficieVivienda = superficieVivienda;
	}

	public String getEstructura() {
		return estructura;
	}

	public void setEstructura(String estructura) {
		this.estructura = estructura;
	}

	public Integer getEdificaValoraReposicion() {
		return edificaValoraReposicion;
	}

	public void setEdificaValoraReposicion(Integer edificaValoraReposicion) {
		this.edificaValoraReposicion = edificaValoraReposicion;
	}

	public Integer getMobiliarioEnseres() {
		return mobiliarioEnseres;
	}

	public void setMobiliarioEnseres(Integer mobiliarioEnseres) {
		this.mobiliarioEnseres = mobiliarioEnseres;
	}

	public Integer getRcEdificacion() {
		return rcEdificacion;
	}

	public void setRcEdificacion(Integer rcEdificacion) {
		this.rcEdificacion = rcEdificacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	public Cliente getTomadorSeguro() {
		return tomadorSeguro;
}
	  
	  public void setTomadorSeguro(Cliente tomadorSeguro) { 
		  this.tomadorSeguro =  tomadorSeguro;
		  }

	public Mediador getAsegurador() {
		return asegurador;
	}

	public void setAsegurador(Mediador asegurador) {
		this.asegurador = asegurador;
	}
	 
	
	
	
	
	
}
