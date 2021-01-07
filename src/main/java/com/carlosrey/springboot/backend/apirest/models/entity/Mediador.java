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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Entity
@Table(name="mediador")
public class Mediador implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_mediador")
	private Long idMediador;
	
	@Column(nullable = false)
	@NotEmpty
	@Size(min=1 , max = 20)
	private String nombre;
	
	@Column(nullable = true)
	@Size(min=1 , max = 30)
	private String apellido;
	
	@Column(nullable = true)
	@Email
	private String email;
	
	
	@Column(nullable = true)
	private String nif;
	
	@Column(nullable = true)
	private String telefono;
	
	@Column(nullable = true)
	private Integer codMediador;
		
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	private Boolean activo;
	
    @JoinColumn(name="id_domicilio", referencedColumnName = "id_domicilio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Domicilio domicilio;
	
	public Mediador() {

	}

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public Long getIdMediador() {
		return idMediador;
	}


	public void setIdMediador(Long idmediador) {
		this.idMediador = idmediador;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Integer getCodMediador() {
		return codMediador;
	}

	public void setCodMediador(Integer codMediador) {
		this.codMediador = codMediador;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}




	private static final long serialVersionUID = 1L;
}
