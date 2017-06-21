package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ManagerTest {
	Manager manager;
	Restaurant r= new Restaurant();
	
	@Before
	public void setUp(){		
		manager = new Manager("proba@proba.com","proba","ime", "prezime");
	}
	
	@Test
	public void testManagerEmail(){
		assertEquals("proba@proba.com", manager.getEmail());
	}
	
	@Test
	public void testManagerPassword(){
		assertEquals("proba", manager.getPassword());
	}
	
	@Test
	public void testManagerFirstName() {
		assertEquals("ime", manager.getFirstName());

	}
	
	@Test
	public void testManagerLastName(){
		assertEquals("prezime", manager.getLastName());
	}
/*	
	@Test
	public void testManagerRestaurant() {
		assertEquals(r, manager.getRestaurant());

	}*/
	
	
	@Test
	public void testManagerNotNull(){
		assertNotNull(manager);
	}

}
