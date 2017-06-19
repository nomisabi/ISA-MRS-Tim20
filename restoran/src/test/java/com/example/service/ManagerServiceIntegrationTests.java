package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.DrinkMenu;
import com.example.domain.Employee;
import com.example.domain.EmployeeSchedule;
import com.example.domain.Manager;
import com.example.domain.Menu;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.MenuRepository;
import com.example.respository.SupllierRepository;

import scala.collection.immutable.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerServiceIntegrationTests {

	@Autowired
	ManagerService service;
	
	@Autowired
	EmployeeService eservice;
	
	@Autowired
	SupplierService sservice;
	
	@Autowired
	RestaurantService rservice;

	Manager dm;
	Restaurant r;

	@Before
	public void setUp() {
		dm= new Manager();
		dm.setEmail("nk");
		dm.setPassword("nk");
		dm = service.createManager(dm);
		 r= new Restaurant();
		r.setName("r1");
		Set<Manager> man;
		man= new java.util.HashSet<Manager>();
		man.add(dm);
		r.setManager(man);
		rservice.createRestaurant(r);
	}


	@Test
	public void findAllManager() {
		assertEquals(10, service.findAll().size());
	}

	
	@Test
	public void createManager() {
		Manager d1= new Manager();
		d1.setEmail("nk");
		d1.setPassword("nk");
		Manager saved= service.createManager(d1);
		assertEquals(d1, saved);
	}
	

	@Test
	public void findManager() {
		assertEquals(dm.getId(), service.findOne(dm.getId()).getId());
	}
	

	@Test
	public void isManagerExist() {
		Manager d1= new Manager();
		d1.setEmail("nk");
		d1.setPassword("nk");
		Manager saved= service.createManager(d1);
		assertEquals(true, service.isManagerExist(d1.getId()));
	}

	
	@Test
	public void updateManager() {
		Manager d1= new Manager();
		d1.setEmail("nk");
		d1.setPassword("nk");
		Manager saved= service.createManager(d1);
		d1.setEmail("123");
		service.update(d1);
		Manager find= service.findOne(d1.getId());
		assertEquals( "123", find.getEmail());
	}

	@Test
	public void createEmployee() {
		int size= eservice.findAll().size();
		Employee d1= new Employee();
		d1.setEmail("nk");
		d1.setPassword("nk");
		service.createEmployee(d1);
		assertEquals(size+1, eservice.findAll().size());
	}

	
	@Test
	public void createSupplier() {
		int size= sservice.findAll().size();
		Supplier d1= new Supplier();
		d1.setEmail("nk");
		service.createSupplier(d1);
		assertEquals(size+1, sservice.findAll().size());
	}

	@Test
	public void findRest() {
	
		Restaurant rest = service.findRest(dm.getId());
		assertEquals(rest.getId(), r.getId());
	}
	
}
