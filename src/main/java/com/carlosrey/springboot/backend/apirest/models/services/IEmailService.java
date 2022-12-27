package com.carlosrey.springboot.backend.apirest.models.services;


public interface IEmailService {

	void processSendEmail(final String receiver, String TemplateCode, String currentName)
			throws Exception;
}
