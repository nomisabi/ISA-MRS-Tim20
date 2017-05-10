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
	public void testTableNumber() {
		assertEquals(1, table.getNumber());
	}

	@Test
	public void testTableNumberOfChairs() {
		assertEquals(5, table.getNumberOfChairs());
	}

	@Test
	public void testGetRestaurant() {
		assertEquals(restaurant, table.getRestaurant());
	}

	@Test
	public void testTableNotNull() {
		assertNotNull(table);
	}
}
