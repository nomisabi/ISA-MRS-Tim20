package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RestaurantTest {
	
	Restaurant resturant;
	ArrayList<Manager> m= new ArrayList<Manager>();
	
	@Before
	public void setUp(){
		resturant = new Restaurant("Resturant1","NN", m);
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
	public void testManagers(){
		assertEquals(m, resturant.getManager());
	}
	
	@Test
	public void TestResurantNotNull(){
		assertNotNull(resturant);
	}

}
