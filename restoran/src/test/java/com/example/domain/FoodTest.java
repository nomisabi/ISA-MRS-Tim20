package com.example.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FoodTest {
	
	Food f;
	
	@Before
	public void setUp(){
		f = new Food("Pizza","Pica",300.00);
	}
	
	@Test
	public void TestFoodName() {
		assertEquals("Pizza", f.getName());
	}
	
	@Test
	public void TestFoodDescription() {
		assertEquals("Pica", f.getDescription());
	}
	
	@Test
	public void TestFoodPrice() {
		assertEquals(300,00, f.getPrice());
	}
}
