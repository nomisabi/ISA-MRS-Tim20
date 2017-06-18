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
import com.example.domain.Menu;
import com.example.domain.Supplier;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.MenuRepository;
import com.example.respository.SupllierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuIntegrationTests {

	@Autowired
	MenuRepository repository;

	Menu dm;

	@Before
	public void setUp() {
		dm= new Menu();
		dm = repository.save(dm);
	}

	@Test
	public void getMenu() {
		Menu sFind = repository.findOne(dm.getId());
		assertNotNull(sFind);
	}

	@Test
	public void createMenu() {
		Menu s1 = new Menu();
		Menu savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteMenu() {
		Menu s2 = new Menu();
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}

	@Test
	public void updateMenu() {
		Date d= new Date();
		repository.update(dm.getId(), d);
		Menu sFind = repository.findOne(dm.getId());
		assertEquals(d, sFind.getDateUpdate());
	}
}
