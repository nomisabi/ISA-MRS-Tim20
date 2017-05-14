package com.example.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.domain.Guest;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment env;
	
	@Async
	public void sendMail(Guest guest, Long id) throws MailException, InterruptedException{
		String token = UUID.randomUUID().toString();
		String subject = "Invite to the restaurant";
		System.out.println(token);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(guest.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(subject);
		mail.setText("http://localhost:8080/#/guest/index/" + id);
	//	javaMailSender.send(mail);
		
		System.out.println("email poslat");
		
	}

}
