package com.example.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DrinkMenuItemTest {

	DrinkMenuItem dmi;
	Drink d;

	@Before
	public void setUp(){
		dmi= new DrinkMenuItem();
		d= new Drink("1","1");
		dmi.setDrink(d);
		dmi.setId((long) 1);
		dmi.setPrice(5.5);
	}
	
	@Test
	public void TestId() {
		Long id=(long) 1;
		assertEquals(id, dmi.getId());
	}

	@Test
	public void TestDrink() {
		assertEquals(d, dmi.getDrink());
	}
	
	@Test
	public void TestPrice() {
		assertEquals(5,5 , dmi.getPrice());
	}
}
