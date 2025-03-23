package com.websitebuilder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class emailService {

	@Autowired()
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String toEmail,String subject,String body)
	{
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("bhagyeshmorvadiya213@gmail.com");
		message.setTo(toEmail);
		message.setText(subject);
		message.setSubject(body);
		
		javaMailSender.send(message);
		
	}
}
