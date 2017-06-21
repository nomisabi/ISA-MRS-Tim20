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
import com.example.domain.EmployeeSchedule;
import com.example.domain.Supplier;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.EmployeeScheduleRepository;
import com.example.respository.SupllierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeScheduleRepositoryIntegrationTests {

	@Autowired
	EmployeeScheduleRepository repository;

	EmployeeSchedule dm;

	@Before
	public void setUp() {
		dm= new EmployeeSchedule();
		dm = repository.save(dm);
	}

	@Test
	public void getEmployeeSchedule() {
		EmployeeSchedule sFind = repository.findOne(dm.getId());
		assertNotNull(sFind);
	}

	@Test
	public void createEmployeeSchedule() {
		EmployeeSchedule s1 = new EmployeeSchedule();
		EmployeeSchedule savedS = repository.save(s1);
		assertEquals(s1, savedS);
	}

	@Test
	public void deleteEmployeeSchedule() {
		EmployeeSchedule s2 = new EmployeeSchedule();
		repository.save(s2);
		repository.delete(s2.getId());
		assertNull(repository.findOne(s2.getId()));
	}

}
