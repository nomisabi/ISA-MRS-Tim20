package com.example.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DrinkMenuTest {

	DrinkMenu dm;
	Date date;
	Set<DrinkMenuItem> dmi;

	@Before
	public void setUp(){
		dm= new DrinkMenu();
		date= new Date();
		dm.setDateUpdate(date);		
		dm.setId((long) 1);
		dmi=new HashSet<DrinkMenuItem>();
		dm.setItems( dmi);
	}
	
	@Test
	public void TestId() {
		Long id=(long) 1;
		assertEquals(id, dm.getId());
	}

	@Test
	public void TestItems() {
		assertEquals(dmi, dm.getItems());
	}
	
	@Test
	public void TestDate() {
		assertEquals(date, dm.getDateUpdate());
	}
	
}
