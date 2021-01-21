package com.carlosrey.springboot.backend.apirest.models.services.impl;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.carlosrey.springboot.backend.apirest.models.dao.INotificationDao;
import com.carlosrey.springboot.backend.apirest.models.entity.EmailTemplate;
import com.carlosrey.springboot.backend.apirest.models.entity.Notificacion;
import com.carlosrey.springboot.backend.apirest.models.services.IEmailService;

/**
 * @author Carlos Rey Silva 
 * https://github.com/carlos1811
 */

@Service
public class EmailServiceImpl implements IEmailService{
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private INotificationDao iNotificationDao;
	
	public void processSendEmail(String receiver, String templateCode, String currentName)
			throws Exception {
		
		final EmailTemplate emailTemplate = findTemplateAndReplace(templateCode, currentName);
		
		this.sendEmail(receiver, emailTemplate.getSubject(), emailTemplate.getTemplate());

	}
	
	private void sendEmail(final String receiver, final String subject, final String template) throws Exception{
		
		final MimeMessage email = javaMailSender.createMimeMessage();
		final MimeMessageHelper content;
		
		try {
			
			content = new MimeMessageHelper(email, true);
			content.setSubject(subject);
			content.setTo(receiver);
			content.setText(template,true);
						
		}catch (Exception e) {
			
			logger.error("ERROR",e);
			throw new Exception("");
		}
		
		javaMailSender.send(email);
		
	}
	
	private EmailTemplate findTemplateAndReplace ( final String TemplateType, final String currentName) throws Exception
	{
		
		
		
		final Notificacion notificacion = iNotificationDao.findByTemplateType(TemplateType);
		
		
		
		final EmailTemplate emailTemplate = new EmailTemplate();
		emailTemplate.setSubject(notificacion.getTemplateType());
		emailTemplate.setTemplate(notificacion.getTemplate()
				.replaceAll("\\{"+"name"+"\\}",currentName));
		
		return emailTemplate;
	}

}
