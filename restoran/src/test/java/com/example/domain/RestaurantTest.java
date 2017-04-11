package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RestaurantTest {
	
	Restaurant resturant;
	
	@Before
	public void setUp(){
		resturant = new Restaurant("Resturant1","NN");
	}
	
	@Test
	public void testName(){
		assertEquals("Resturant1", resturant.getName());
	}
	
	@Test
	public void testLocation(){
		assertEquals("NN", resturant.getLocation());
	}
	
	@Test
	public void TestResurantNotNull(){
		assertNotNull(resturant);
	}

}
