package com.example.domain.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Employee;
import com.example.domain.Restaurant;
import com.example.domain.DTOs.EmployeeRestaurant;

@RunWith(SpringRunner.class)
public class EmployeeRestaurantTest {

	EmployeeRestaurant er;
	Employee e;
	Restaurant r;
	
	@Before
	public void setUp(){
		e= new Employee();
		r= new Restaurant();
		er= new EmployeeRestaurant(r, e);
	}
	
	@Test
	public void TestEmployee() {
		assertEquals(e, er.getE());
	}
	
	@Test
	public void TestRest() {
		assertEquals(r, er.getR());
	}
	
	@Test
	public void TesterinkRestaurant() {
		assertNotNull(er);
	}
}
