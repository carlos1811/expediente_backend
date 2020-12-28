/**
 * 
 */
package com.carlosrey.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author carlos pc
 *
 */
@Entity
@Table(name="factura_items")
public class ItemFactura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Integer cantidad;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_producto",referencedColumnName = "id_producto")
	private Producto producto;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
	public Double calcularImporte() {
		return cantidad.doubleValue();
		
	}
	
	public Double getImporte() {
		
		return cantidad.doubleValue() * producto.getPrecio();
	}
	
	
	public Producto getProducto() {
		
		return producto;
		
	}
	
	
}