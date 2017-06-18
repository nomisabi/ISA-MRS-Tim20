package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Guest;
import com.example.domain.RegistrationToken;
import com.example.respository.GuestRepository;
import com.example.respository.RegistrationTokenRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationTokenRepositoryIntegrationTests {
	@Autowired
	RegistrationTokenRepository repostitory;
	@Autowired
	GuestRepository guestRepository;

	Guest guest;
	RegistrationToken token;

	@Before
	public void setUp() {
		guest = new Guest("novi@gmail.com", "123456", "proba", "proba1");
		guest = guestRepository.save(guest);
		token = new RegistrationToken(guest, UUID.randomUUID().toString());
		repostitory.save(token);
	}

	@Test
	public void get() {
		RegistrationToken find = repostitory.findOne(token.getId());
		assertNotNull(find);
	}

	@Test
	public void getGuestId() {
		Guest g = repostitory.getGuest(token.getToken());
		assertNotNull(g);
		assertEquals(g.getId(), guest.getId());
	}
	
	@Test
	public void getId() {
		Long id = repostitory.getId(token.getToken());
		assertEquals(id, token.getId());
	}

}
