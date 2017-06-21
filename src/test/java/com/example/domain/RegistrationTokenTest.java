package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RegistrationTokenTest {
	RegistrationToken registrationToken;
	Guest guest;
	String token;

	@Before
	public void setUp() {
		guest = new Guest("proba@proba.com", "123456");
		token = UUID.randomUUID().toString();
		registrationToken = new RegistrationToken(guest, token);
	}
	
	@Test
	public void testGetGuest() {
		assertEquals(guest, registrationToken.getGuest());
	}

	@Test
	public void testGetToken() {
		assertEquals(token, registrationToken.getToken());
	}
	
	@Test
	public void testSetGuest() {
		Guest g = new Guest();
		registrationToken.setGuest(g);
		assertEquals(g, registrationToken.getGuest());
	}

	@Test
	public void testSetToken() {
		String t = UUID.randomUUID().toString();
		registrationToken.setToken(t);
		assertEquals(t, registrationToken.getToken());
	}
	
	@Test
	public void testNotNull() {
		assertNotNull(registrationToken);
	}


}
