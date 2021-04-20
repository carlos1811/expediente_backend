package com.carlosrey.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name="factura")
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_Factura")
	private Long idFactura;
	
	@Column(nullable = false)
	private String nombreFactura;
	
	
	private String descripcion;
	
	private String observacion;
	
	private BigDecimal costeTotal;
	
	private Integer impuestos;
	
	private Date fechaFactura;


	@Column(nullable = false, unique = true)
	private String emailCliente;
	
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_id")
	private List<ItemFactura> items;
	
    @JoinColumn(name="id_cliente",referencedColumnName = "id_expediente")
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Expediente expediente;
		
	public Factura() {
	
		items = new ArrayList<>();
		
	}

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}
	
	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
	
	public Double getTotal() {
		
		Double total = 0.00;
		
	for (ItemFactura item : items) {
		total += item.getImporte();
	}
		return total;
	}

	private static final long serialVersionUID = 1L;
}
