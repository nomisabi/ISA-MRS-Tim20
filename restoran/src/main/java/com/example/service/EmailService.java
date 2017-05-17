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
import com.example.domain.GuestReservation;
import com.example.domain.VerificationToken;
import com.example.respository.VerificationTokenRepository;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment env;
	@Autowired
	private VerificationTokenRepository repository;

	@Async
	public void sendMail(GuestReservation guestReservation,Guest guest, Guest friend) throws MailException, InterruptedException {
		String token = UUID.randomUUID().toString();
		VerificationToken verification = new VerificationToken(guestReservation, token);
		repository.save(verification);

		String subject = "Invite to the restaurant";
		System.out.println(token);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(friend.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(subject);
		String text = "You are invited to the restaurant by " + guest.getFirstName() + " " + guest.getLastName()
				+ "\n\n Click on the link for more details:   ";
		mail.setText(text + "http://localhost:8080/#/guest/index/" + token);
		//javaMailSender.send(mail);

		System.out.println("email poslat");

	}

}
