package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class VerificationTokenTest {

	VerificationToken verificationToken;
	GuestReservation guestReservation;
	Guest guest;
	Reservation reservation;
	String token;

	@Before
	public void setUp() {
		guest = new Guest("proba@proba.com", "123456");
		Restaurant restaurant = new Restaurant("proba", "proba");
		reservation = new Reservation(restaurant, "2017-05-15 12:00", "2017-05-15 14:00");
		guestReservation = new GuestReservation(guest, reservation);
		token = UUID.randomUUID().toString();
		verificationToken = new VerificationToken(guestReservation, token);
	}

	@Test
	public void testGuestReservation() {
		assertEquals(guestReservation, verificationToken.getGuestReservation());
	}

	@Test
	public void testToken() {
		assertEquals(token, verificationToken.getToken());
	}

	@Test
	public void testNotNull() {
		assertNotNull(token);
	}

}
