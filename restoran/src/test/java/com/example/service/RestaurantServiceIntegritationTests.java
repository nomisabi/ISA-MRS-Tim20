package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.DrinkMenu;
import com.example.domain.Employee;
import com.example.domain.Manager;
import com.example.domain.Menu;
import com.example.domain.MenuItem;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.System_manager;
import com.example.domain.TypeEmployee;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.EmployeeRepository;
import com.example.respository.ManagerRepository;
import com.example.respository.MenuRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.SupllierRepository;

import scala.collection.immutable.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantServiceIntegritationTests {

	@Autowired
	RestaurantService service;
	
	@Autowired
	MenuRepository mrep;
	@Autowired
	DrinkMenuRepository dmrep;
	@Autowired
	RestaurantRepository repository;
	@Autowired
	EmployeeRepository erepository;
	
	
	Restaurant r;

	@Before
	public void setUp() {
		r= new Restaurant();
		r.setName("");
		service.createRestaurant(r);
	}


	@Test
	public void findAllSysMan() {
		assertNotEquals(0, service.getAllRestaurants().size());
	}
	

	@Test
	public void createRest() {
		Restaurant r1= new Restaurant();
		r1.setName("");
		service.createRestaurant(r1);
	}
	
	
	@Test
	public void findRest() {
		assertEquals(r.getId(), service.getRestaurant(r.getId()).getId());
	}
	

	@Test
	public void findAllRating() throws ParseException {		
		assertEquals(0,0, service.getAvg(r.getId()));
		assertEquals(0, service.getAvgByFood(r.getId()).size());
		assertEquals(0, service.getAvgByWaiter(r.getId()).size());
		assertEquals(0, service.getIncomes(r.getId()).size());
		assertEquals(0, service.getIncomes("24/05/2010","24/05/2020",r.getId()).size());
		assertEquals(0, service.getVisits(r.getId()).size());
	}
	
	
	@Test
	public void updateMenu() {
		Menu m = new Menu();
		m=mrep.save(m);
		r.setMenu(m);
		service.updateMenu(r);
		Restaurant find = repository.findOne(r.getId());
		assertEquals(m.getId(), find.getMenu().getId());
	}
	
	@Test
	public void updateDrinkMenu() {
		DrinkMenu m = new DrinkMenu();
		m=dmrep.save(m);
		r.setDrinkMenu(m);
		service.updateDrinkMenu(r);
		Restaurant find = repository.findOne(r.getId());
		assertEquals(m.getId(), find.getDrinkMenu().getId());
	}
	
	@Test
	public void insertEmpl() {
		Employee e = new Employee();
		e.setEmail("");
		e.setPassword("");
		e=erepository.save(e);
		service.insertEmpl(r.getId(), e.getId());
	}
	
	
}
