package com.example.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TableTest {
	Table table;
	
	@Before
	public void setUp(){
		table = new Table(1,1,4,true);
	}
	
	@Test
	public void testX() {
		assertEquals(1, table.getX());
	}
	
	@Test
	public void testY() {
		assertEquals(1, table.getY());
	}
	
	@Test
	public void testNumberOfChairs() {
		assertEquals(4, table.getNumChairs());
	}
	
	@Test
	public void testOcuppied() {
		assertEquals(false, table.isOcupied());
	}
	

}
