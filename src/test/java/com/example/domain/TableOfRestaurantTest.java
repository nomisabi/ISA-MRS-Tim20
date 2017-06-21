package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TableOfRestaurantTest {

	TableOfRestaurant table;
	Restaurant restaurant;

	@Before
	public void setUp() {
		restaurant = new Restaurant("Restaurant", "proba");
		table = new TableOfRestaurant(1, 5, restaurant);
	}

	@Test
	public void testGetTableNumber() {
		assertEquals(1, table.getNumber());
	}

	@Test
	public void testGetNumberOfChairs() {
		assertEquals(5, table.getNumberOfChairs());
	}

	@Test
	public void testGetRestaurant() {
		assertEquals(restaurant, table.getRestaurant());
	}
	
	@Test
	public void testSetTableNumber() {
		table.setNumber(2);
		assertEquals(2, table.getNumber());
	}

	@Test
	public void testSetNumberOfChairs() {
		table.setNumberOfChairs(8);
		assertEquals(8, table.getNumberOfChairs());
	}

	@Test
	public void testSetRestaurant() {
		Restaurant r = new Restaurant();
		table.setRestaurant(r);
		assertEquals(r, table.getRestaurant());
	}

	@Test
	public void testTableNotNull() {
		assertNotNull(table);
	}
}
