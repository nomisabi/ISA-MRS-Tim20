package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.DrinkMenu;
import com.example.domain.Supplier;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.SupllierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrinkMenuIntegrationTests {

	@Autowired
	DrinkMenuRepository repository;

	DrinkMenu dm;

	@Before
	public void setUp() {
		dm= new DrinkMenu();
		dm = repository.save(dm);
	}

	@Test
	public void getDrinkMenu() {
		DrinkMenu sFind = repository.findOne(dm.getId());
		assertNotNull(sFind);
	}

	@Test
	public void createDrinkMenu() {
		DrinkMenu s1 = new DrinkMenu();
		DrinkMenu savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteDrinkMenu() {
		DrinkMenu s2 = new DrinkMenu();
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}

	@Test
	public void updateDrinkMenu() {
		Date d= new Date();
		repository.update(dm.getId(), d);
		DrinkMenu sFind = repository.findOne(dm.getId());
		assertEquals(d, sFind.getDateUpdate());
	}
}
