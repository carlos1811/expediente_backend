package com.carlosrey.springboot.backend.apirest.models.services;


public interface IEmailService {

	public String processSendEmail(final String receiver, final String subject, String TemplateCode, String currentName) 
			throws Exception;
}
