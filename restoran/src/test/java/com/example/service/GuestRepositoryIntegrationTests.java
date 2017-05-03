package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Guest;
import com.example.respository.GuestRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestRepositoryIntegrationTests {
	
	@Autowired
	GuestRepository repository;
	
	@Test
	public void getGuest() {
		Guest guest = repository.findOne(1L);
		assertNotNull(guest);
	}
	
	@Test
	public void createGuest() {
		Guest guest = new Guest("novi@gmail.com", "145564");
		Guest savedGuest = repository.save(guest);
		assertEquals(guest, savedGuest);
	}
	
	@Test
	public void updateGuest() {
		repository.updateGuest(1L, "novi@gmail.com", "Nena", "Djeric","address");
		Guest updateGuest = repository.findOne(1L);
		assertEquals("novi@gmail.com", updateGuest.getEmail());
		assertEquals("Nena", updateGuest.getFirstName());
		assertEquals("Djeric", updateGuest.getLastName());
		assertEquals("address", updateGuest.getAddress());
	}
}
