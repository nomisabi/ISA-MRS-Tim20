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
import com.example.domain.Employee;
import com.example.domain.Supplier;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.EmployeeRepository;
import com.example.respository.SupllierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryIntegrationTests {

	@Autowired
	EmployeeRepository repository;

	Employee dm;

	@Before
	public void setUp() {
		dm= new Employee();
		dm.setEmail("");
		dm.setPassword("");
		dm = repository.save(dm);
	}

	@Test
	public void getEmployee() {
		Employee sFind = repository.findOne(dm.getId());
		assertNotNull(sFind);
	}

	@Test
	public void createEmployee() {
		Employee s1 = new Employee();
		s1.setEmail("");
		s1.setPassword("");
		Employee savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteEmployee() {
		Employee s2 = new Employee();
		s2.setEmail("");
		s2.setPassword("");
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}

}
