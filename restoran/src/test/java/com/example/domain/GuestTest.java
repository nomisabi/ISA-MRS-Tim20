package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GuestTest {
	Guest guest;
	
	@Before
	public void setUp(){
		guest = new Guest("proba@proba.com","proba","Proba","ProbaLastName");
	}
	
	@Test
	public void testGuestEmail(){
		assertEquals("proba@proba.com", guest.getEmail());
	}
	
	@Test
	public void testGuestPassword(){
		assertEquals("proba", guest.getPassword());
	}
	
	@Test
	public void testGuestFirstName() {
		assertEquals("Proba", guest.getFirstName());

	}
	
	@Test
	public void testGuestLastName(){
		assertEquals("ProbaLastName", guest.getLastName());
	}
	
	@Test
	public void testGuestNotNull(){
		assertNotNull(guest);
	}

}
