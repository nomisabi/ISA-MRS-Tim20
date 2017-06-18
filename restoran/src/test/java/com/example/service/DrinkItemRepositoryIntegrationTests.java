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
import com.example.domain.Supplier;
import com.example.respository.DrinkItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrinkItemRepositoryIntegrationTests {

	@Autowired
	DrinkItemRepository rep;
	DrinkMenuItem dmi;
	
	@Before
	public void setUp() {
		dmi= new DrinkMenuItem();
		rep.save(dmi);
	}

	@Test
	public void getDMI() {
		DrinkMenuItem dmiFind = rep.findOne(1L);
		assertNotNull(dmiFind);
	}
	
	@Test
	public void saveDMI() {
		DrinkMenuItem dmi1 = new DrinkMenuItem();
		DrinkMenuItem savedDmi = rep.save(dmi1);
		assertEquals(dmi1, savedDmi);
	}
	
	@Test
	public void deleteDMI() {
		DrinkMenuItem s2 = new DrinkMenuItem();
		rep.save(s2);
		rep.delete(s2.getId());
		assertNull(rep.findOne(s2.getId()));
	}
	
	@Test
	public void updateDMI() {
		rep.update(dmi.getId(),4.4);
		DrinkMenuItem updateS = rep.findOne(dmi.getId());
		assertEquals(4,4, updateS.getPrice());
	}
	
}
