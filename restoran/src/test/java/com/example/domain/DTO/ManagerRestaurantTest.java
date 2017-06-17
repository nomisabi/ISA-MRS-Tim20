package com.example.domain.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Employee;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.DTOs.EmployeeRestaurant;
import com.example.domain.DTOs.ManagerRestaurant;

@RunWith(SpringRunner.class)
public class ManagerRestaurantTest {

	ManagerRestaurant mr;
	Manager e;
	Restaurant r;
	
	@Before
	public void setUp(){
		e= new Manager();
		r= new Restaurant();
		mr= new ManagerRestaurant(r, e);
	}
	
	@Test
	public void TestEmployee() {
		assertEquals(e, mr.getM());
	}
	
	@Test
	public void TestRest() {
		assertEquals(r, mr.getR());
	}
	
	@Test
	public void TestManRestaurant() {
		assertNotNull(mr);
	}
}
