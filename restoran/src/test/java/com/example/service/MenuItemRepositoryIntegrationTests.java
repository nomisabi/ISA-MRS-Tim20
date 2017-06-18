package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.DrinkMenuItem;
import com.example.domain.MenuItem;
import com.example.domain.Supplier;
import com.example.respository.DrinkItemRepository;
import com.example.respository.MenuItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuItemRepositoryIntegrationTests {

	@Autowired
	MenuItemRepository rep;
	MenuItem dmi;
	
	@Before
	public void setUp() {
		dmi= new MenuItem();
		rep.save(dmi);
	}

	@Test
	public void getDMI() {
		MenuItem dmiFind = rep.findOne(1L);
		assertNotNull(dmiFind);
	}
	
	@Test
	public void saveDMI() {
		MenuItem dmi1 = new MenuItem();
		MenuItem savedDmi = rep.save(dmi1);
		assertEquals(dmi1, savedDmi);
	}
	
	@Test
	public void deleteDMI() {
		MenuItem s2 = new MenuItem();
		rep.save(s2);
		rep.delete(s2.getId());
		assertNull(rep.findOne(s2.getId()));
	}
	
	@Test
	public void updateDMI() {
		rep.update(dmi.getId(),4.4);
		MenuItem updateS = rep.findOne(dmi.getId());
		assertEquals(4,4, updateS.getPrice());
	}
	
}
