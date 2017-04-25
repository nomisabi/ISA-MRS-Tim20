package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmployeeTest {
	Employee em;
	Date date;
	
	@Before
	public void setUp(){
		date=new Date();		
		em = new Employee("firstName","lastName","email","password",date, 43,43,TypeEmployee.WAITER);
	}
	
	@Test
	public void testEMEmail(){
		assertEquals("email", em.getEmail());
	}
	
	@Test
	public void testEMPassword(){
		assertEquals("password", em.getPassword());
	}
	
	@Test
	public void testEMFirstName() {
		assertEquals("firstName", em.getFirstName());

	}
	
	@Test
	public void testEMLastName(){
		assertEquals("lastName", em.getLastName());
	}
	
	@Test
	public void testEMDate(){
		assertEquals(date, em.getDate());
	}
	
	@Test
	public void testEMNumbS(){
		assertEquals(43, em.getNumbS());
	}
	
	@Test
	public void testEMNumbC() {
		assertEquals(43, em.getNumbC());

	}
	
	@Test
	public void testEMType(){
		assertEquals(TypeEmployee.WAITER, em.getType());
	}
	
	@Test
	public void testEMNotNull(){
		assertNotNull(em);
	}
}
