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
public class MenuTest {

	Menu m;
	Date date;
	Set<MenuItem> mi;

	@Before
	public void setUp(){
		m= new Menu();
		date= new Date();
		m.setDateUpdate(date);		
		m.setId((long) 1);
		mi=new HashSet<MenuItem>();
		m.setItems( mi);
	}
	
	@Test
	public void TestId() {
		Long id=(long) 1;
		assertEquals(id, m.getId());
	}

	@Test
	public void TestItems() {
		assertEquals(mi, m.getItems());
	}
	
	@Test
	public void TestDate() {
		assertEquals(date, m.getDateUpdate());
	}
	
}
