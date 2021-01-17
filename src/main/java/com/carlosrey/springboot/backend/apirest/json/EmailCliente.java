package com.carlosrey.springboot.backend.apirest.json;

import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	
}
