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
import com.example.domain.DTOs.FoodRate;

@RunWith(SpringRunner.class)
public class FoodRateTest {
	FoodRate fr;
	
	@Before
	public void setUp(){
		fr=new FoodRate("fr", 3.4);
	}
	
	@Test
	public void TestName() {
		assertEquals("fr", fr.getName());
	}
	
	@Test
	public void TestRate() {
		assertEquals(3,4 , fr.getRate());
	}
	
	@Test
	public void TestFoodRate() {
		assertNotNull(fr);
	}
}
