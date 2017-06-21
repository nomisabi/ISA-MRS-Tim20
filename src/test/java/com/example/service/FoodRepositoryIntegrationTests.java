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

import com.example.domain.Food;
import com.example.domain.Supplier;
import com.example.respository.FoodRepository;
import com.example.respository.SupllierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodRepositoryIntegrationTests {

	@Autowired
	FoodRepository repository;

	Food dm;

	@Before
	public void setUp() {
		dm= new Food();
		dm = repository.save(dm);
	}

	@Test
	public void getFood() {
		Food sFind = repository.findOne(dm.getId());
		assertNotNull(sFind);
	}

	@Test
	public void createFood() {
		Food s1 = new Food();
		Food savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteFood() {
		Food s2 = new Food();
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}

	@Test
	public void updateFood() {
		Date d= new Date();
		repository.update(dm.getId(), "name", "desc");
		Food sFind = repository.findOne(dm.getId());
		assertEquals("name", sFind.getName());
		assertEquals("desc", sFind.getDescription());
	}
}
