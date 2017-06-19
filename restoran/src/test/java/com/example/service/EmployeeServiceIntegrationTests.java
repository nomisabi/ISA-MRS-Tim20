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
import com.example.domain.EmployeeSchedule;
import com.example.domain.Supplier;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.EmployeeRepository;
import com.example.respository.SupllierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceIntegrationTests {

	@Autowired
	EmployeeService service;
	
	@Autowired
	EmployeeRepository repository;

	Employee dm;

	@Before
	public void setUp() {
		dm= new Employee();
		dm.setEmail("123");
		dm.setPassword("123");
		dm = repository.save(dm);
	}


	@Test
	public void findAllEmployee() {
		assertEquals(4, service.findAll().size());
	}
	

	@Test
	public void findEmployeeSchedule() {
		assertEquals(dm.getId(), service.findOne(dm.getId()).getId());
	}
	

}
