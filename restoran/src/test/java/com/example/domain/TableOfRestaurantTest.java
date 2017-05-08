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

	@Before
	public void setUp() {
		table = new TableOfRestaurant(1, 5, new Restaurant());
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
	public void testTableNotNull() {
		assertNotNull(table);
	}
}
