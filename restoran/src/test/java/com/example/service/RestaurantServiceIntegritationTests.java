package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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
	RestaurantRepository repository;
	@Autowired
	SupllierRepository srepository;
	@Autowired
	EmployeeRepository erepository;
	@Autowired
	ManagerRepository mrepository;
	@Autowired
	DrinkMenuRepository dmrepository;
	@Autowired
	MenuRepository merepository;

	Restaurant restaurant;

	@Before
	public void setUp() {
		restaurant = new Restaurant("restaurant1", "ns");
		restaurant = repository.save(restaurant);
	}

	@Test
	public void getRestaurant() {
		Restaurant restaurantFind = repository.findOne(restaurant.getId());
		assertNotNull(restaurantFind);
	}

	@Test
	public void createRestaurant() {
		Restaurant restaurant = new Restaurant("proba", "proba");
		Restaurant savedRestaurant = repository.save(restaurant);
		assertEquals(restaurant, savedRestaurant);
	}
	
	@Test
	public void searchRestaurant() {
		Collection<Restaurant> restaurants = repository.searchRestaurants(restaurant.getName());
		assertNotEquals(0, restaurants.size());
	}
	
	@Test
	public void getRest() {
		Supplier s= new Supplier();
		s=srepository.save(s);
		Set<Supplier> sup = new HashSet<Supplier>();
		sup.add(s);
		restaurant.setSuppliers(sup);
		repository.save(restaurant);
		Collection<Restaurant> restaurants = repository.getRest(s.getId());
		assertEquals(1, restaurants.size());
	}

	@Test
	public void insertSup() {
		Supplier s= new Supplier();
		s=srepository.save(s);
	    repository.insertSup(restaurant.getId(), s.getId());
	    Restaurant savedRestaurant = repository.findOne(restaurant.getId());
	    Collection<Restaurant> restaurants = repository.getRest(s.getId());
		assertEquals(1, restaurants.size());
	}
	
	@Test
	public void insertMan() {
		Manager s= new Manager();
		s.setEmail("");
		s.setPassword("");
		s=mrepository.save(s);
	    repository.insertMan(restaurant.getId(), s.getId());
	    Long id= mrepository.getRest(s.getId());
	    Restaurant savedRestaurant = repository.findOne(id);
	    //Set<Manager> man = savedRestaurant.getManager();
		assertEquals(savedRestaurant.getId(), restaurant.getId());
	}
	
	@Test
	public void insertEmpl() {
		Employee s= new Employee();
		s.setEmail("");
		s.setPassword("");
		s.setType(TypeEmployee.BARTENDER);
		s=erepository.save(s);
	    repository.insertEmpl(restaurant.getId(), s.getId());
	    Restaurant savedRestaurant = repository.findOne(restaurant.getId());
		//assertEquals(1,  savedRestaurant.getEmployee().size());
	}
	
	@Test
	public void updateMenu() {
		Menu s= new Menu();
		s=merepository.save(s);
		repository.updateMenu(restaurant.getId(),s.getId());
		Restaurant savedRestaurant = repository.findOne(restaurant.getId());
	    Menu man = savedRestaurant.getMenu();
		assertEquals(man.getId(), s.getId());
	}
	
	@Test
	public void updateDrinkMenu() {
		DrinkMenu s= new DrinkMenu();
		s=dmrepository.save(s);
		repository.updateDrinkMenu(restaurant.getId(),s.getId());
		Restaurant savedRestaurant = repository.findOne(restaurant.getId());
		DrinkMenu man = savedRestaurant.getDrinkMenu();
		assertEquals(man.getId(), s.getId());
	}
	
	
}
