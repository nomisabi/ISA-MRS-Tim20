package com.example.domain.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Bill;
import com.example.domain.Drink;
import com.example.domain.DrinkMenu;
import com.example.domain.Employee;
import com.example.domain.Restaurant;
import com.example.domain.DTOs.DrinkRestaurant;

@RunWith(SpringRunner.class)
public class DrinkRestaurantTest {

	DrinkRestaurant dr;
	DrinkMenu d;
	Restaurant r;
	
	@Before
	public void setUp(){
		d= new DrinkMenu();
		r= new Restaurant();
		dr= new DrinkRestaurant(d, r);
	}
	
	@Test
	public void TestDrinkMenu() {
		assertEquals(d, dr.getD());
	}
	
	@Test
	public void TestRest() {
		assertEquals(r, dr.getR());
	}
	
	@Test
	public void TestDrinkRestaurant() {
		assertNotNull(dr);;
	}
	
}
