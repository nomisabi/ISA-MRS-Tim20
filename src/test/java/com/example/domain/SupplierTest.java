package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SupplierTest {
	Supplier sup;
	
	@Before
	public void setUp(){
		sup = new Supplier("name", "email", "pass");
	}
	
	@Test
	public void testSupplierName(){
		assertEquals("name", sup.getName());
	}
	
	@Test
	public void testSupplierEmail(){
		assertEquals("email", sup.getEmail());
	}
	@Test
	public void testSupplierPassword(){
		assertEquals("pass", sup.getPassword());
	}
	
	@Test
	public void testSupplierNotNull(){
		assertNotNull(sup);
	}
}
