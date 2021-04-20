package com.carlosrey.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class Domicilio implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_domicilio")
  private Integer idDomicilio;
  
  
  private String tipoCalle;
  
  private String calle;
  
  private String municipio;
  
  private String provincia;
  
  private String telefono;
  
  private Boolean activo;
  
  
  public Domicilio(){
      
  }
  
  public Domicilio(Integer idDomicilio){
      this.idDomicilio = idDomicilio;
  }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.idDomicilio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Domicilio other = (Domicilio) obj;
        if (!Objects.equals(this.idDomicilio, other.idDomicilio)) {
            return false;
        }
        return true;
    }
  
  
  
}
