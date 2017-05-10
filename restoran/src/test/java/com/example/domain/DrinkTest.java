package com.example.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DrinkTest {
	
	Drink d;
	
	@Before
	public void setUp(){
		d = new Drink("Turska kafa","Kafa",100.00);
	}
	
	@Test
	public void TestDrinkName() {
		assertEquals("Turska kafa", d.getName());
	}
	
	@Test
	public void TestDrinkDescription() {
		assertEquals("Kafa", d.getDescription());
	}
	
	@Test
	public void TestDrinkPrice() {
		assertEquals(100,00, d.getPrice());
	}
}
