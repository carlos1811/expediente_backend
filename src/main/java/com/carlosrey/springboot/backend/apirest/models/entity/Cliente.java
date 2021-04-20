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
@Table(name="cliente")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_cliente")
	private Long idCliente;
	
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
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	private Boolean activo;
	
	private Date FechaPrueba;
		
    @JoinColumn(name="id_domicilio", referencedColumnName = "id_domicilio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Domicilio domicilio;
	
  //  @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  //  @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
  //  private List<Factura> facturas;
      
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_provincia")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Provincia provincia;
       
	public Cliente() {

//		this.facturas = new ArrayList<>();
	}


	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	/*
	 * public List<Factura> getFacturas() { return facturas; }
	 * 
	 * 
	 * public void setFacturas(List<Factura> facturas) { this.facturas = facturas; }
	 */
	

	private static final long serialVersionUID = 1L;
}
