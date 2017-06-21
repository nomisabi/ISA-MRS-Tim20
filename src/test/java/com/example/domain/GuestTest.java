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
	public void testGetEmail(){
		assertEquals("proba@proba.com", guest.getEmail());
	}
	
	@Test
	public void testGetPassword(){
		assertEquals("proba", guest.getPassword());
	}
	
	@Test
	public void testGetFirstName() {
		assertEquals("Proba", guest.getFirstName());

	}
	
	@Test
	public void testGetLastName(){
		assertEquals("ProbaLastName", guest.getLastName());
	}
	
	@Test
	public void testSetEmail(){
		guest.setEmail("novi@novi.com");
		assertEquals("novi@novi.com", guest.getEmail());
	}
	
	@Test
	public void testSetPassword(){
		guest.setPassword("noviPass");
		assertEquals("noviPass", guest.getPassword());
	}
	
	@Test
	public void testSetFirstName() {
		guest.setFirstName("ime");
		assertEquals("ime", guest.getFirstName());

	}
	
	@Test
	public void testSetLastName(){
		guest.setLastName("prezime");
		assertEquals("prezime", guest.getLastName());
	}
	
	@Test
	public void testIsAccepted(){
		assertEquals(false, guest.isAccepted());
	}
	
	@Test
	public void testSetAccepted(){
		guest.setAccepted(true);
		assertEquals(true, guest.isAccepted());
	}
	
	@Test
	public void testGuestNotNull(){
		assertNotNull(guest);
	}

}
