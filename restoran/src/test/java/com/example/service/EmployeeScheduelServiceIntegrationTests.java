package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Drink;
import com.example.domain.DrinkMenu;
import com.example.domain.DrinkMenuItem;
import com.example.domain.Employee;
import com.example.domain.EmployeeSchedule;
import com.example.domain.Supplier;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.EmployeeRepository;
import com.example.respository.SupllierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeScheduelServiceIntegrationTests {

	@Autowired
	EmployeeScheduleService service;

	EmployeeSchedule dm;

	@Before
	public void setUp() {
		dm= new EmployeeSchedule();
		dm = service.addEmployeeSchedule(dm);
	}


	@Test
	public void findAllEmployeeSchedule() {
		assertEquals(4, service.findAll().size());
	}
	

	
	@Test
	public void createEmployeeSchedule() {
		EmployeeSchedule d1= new EmployeeSchedule();
		EmployeeSchedule saved= service.addEmployeeSchedule(d1);
		assertEquals(d1, saved);
	}
	
	
	@Test
	public void deleteEmployeeSchedule() {
		EmployeeSchedule d1= new EmployeeSchedule();
		EmployeeSchedule saved= service.addEmployeeSchedule(d1);
		service.deleteEmployeeSchedule(saved.getId());
		assertNull(service.findOne(saved.getId()));
	}

	@Test
	public void findEmployeeSchedule() {
		//EmployeeSchedule d1= new EmployeeSchedule();
		//EmployeeSchedule saved= service.createEmployeeSchedule(d1);
		assertEquals(dm.getId(), service.findOne(dm.getId()).getId());
	}
	

	@Test
	public void isEmployeeScheduleExist() {
		EmployeeSchedule d1= new EmployeeSchedule();
		EmployeeSchedule saved= service.addEmployeeSchedule(d1);
		assertEquals(true, service.isEmployeeScheduleExist(d1.getId()));
	}

	
	@Test
	public void updateEmployeeSchedule() {
		EmployeeSchedule d1= new EmployeeSchedule();
		d1.setC1("23");
		EmployeeSchedule saved= service.addEmployeeSchedule(d1);
		d1.setC1("123");
		service.updateEmployeeSchedule(d1);
		EmployeeSchedule find= service.findOne(d1.getId());
		assertEquals( "123", find.getC1());
	}

	


}
