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

import com.example.domain.Drink;
import com.example.domain.DrinkMenu;
import com.example.domain.Supplier;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.SupllierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrinkRepositoryIntegrationTests {

	@Autowired
	DrinkRepository repository;

	Drink dm;

	@Before
	public void setUp() {
		dm= new Drink();
		dm = repository.save(dm);
	}

	@Test
	public void getDrink() {
		Drink sFind = repository.findOne(dm.getId());
		assertNotNull(sFind);
	}

	@Test
	public void createDrink() {
		Drink s1 = new Drink();
		Drink savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteDrink() {
		Drink s2 = new Drink();
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}

	@Test
	public void updateDrink() {
		Date d= new Date();
		repository.update(dm.getId(), "name", "desc");
		Drink sFind = repository.findOne(dm.getId());
		assertEquals("name", sFind.getName());
		assertEquals("desc", sFind.getDescription());
	}
}
