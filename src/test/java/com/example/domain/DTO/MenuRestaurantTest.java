package com.example.domain.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Bill;

import com.example.domain.Menu;
import com.example.domain.Employee;
import com.example.domain.Restaurant;
import com.example.domain.DTOs.MenuRestaurant;

@RunWith(SpringRunner.class)
public class MenuRestaurantTest {

	MenuRestaurant dr;
	Menu d;
	Restaurant r;
	
	@Before
	public void setUp(){
		d= new Menu();
		r= new Restaurant();
		dr= new MenuRestaurant(d, r);
	}
	
	@Test
	public void TestMenu() {
		assertEquals(d, dr.getM());
	}
	
	@Test
	public void TestRest() {
		assertEquals(r, dr.getR());
	}
	
	@Test
	public void TestRestaurant() {
		assertNotNull(dr);;
	}
	
}
