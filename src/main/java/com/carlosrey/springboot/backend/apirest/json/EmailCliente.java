package com.carlosrey.springboot.backend.apirest.json;

import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailCliente {
	
	@JsonProperty("idCliente")
	private Cliente idCliente;
	
	@JsonProperty("nombreCliente")
	private String nombreCliente;
	
	@JsonProperty("template")
	private String template;
	
	@JsonProperty("templateType")
	private String templateType;

}
