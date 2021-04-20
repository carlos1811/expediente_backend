/**
 * 
 */
package com.carlosrey.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author carlos pc
 *
 */

@Setter
@Getter
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
