package com.carlosrey.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="clientes_extension")
public class ClienteExtension implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_cliente_extension")
	private Long idClienteExtension;

    @OneToOne
    @JoinColumn(name="id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;
	
	private String textoArea;


	public String getTextoArea() {
		return textoArea;
	}

	public void setTextoArea(String textoArea) {
		this.textoArea = textoArea;
	}
	
   
	
	
	
}
