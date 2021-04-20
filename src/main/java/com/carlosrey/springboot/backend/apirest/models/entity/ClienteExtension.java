package com.carlosrey.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="clientes_extension")
public class ClienteExtension implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_cliente_extension")
	private Long idClienteExtension;

    @OneToOne
    @JoinColumn(name="id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;
	
	private String textoArea;

	
}
