package com.example.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MenuItemTest {

	MenuItem mi;
	Food f;

	@Before
	public void setUp(){
		mi= new MenuItem();
		f= new Food("1","1");
		mi.setFood(f);
		mi.setId((long) 1);
		mi.setPrice(5.5);
	}
	
	@Test
	public void TestId() {
		Long id=(long) 1;
		assertEquals(id, mi.getId());
	}

	@Test
	public void TestFood() {
		assertEquals(f, mi.getFood());
	}
	
	@Test
	public void TestPrice() {
		assertEquals(5,5 , mi.getPrice());
	}
}
