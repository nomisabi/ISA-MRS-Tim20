package com.example.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RegionTest {
	Region re;
	
	@Before
	public void setUp(){
		//re = new Region(1,2,SMOKING);
	}
	
	@Test
	public void testX() {
		//assertEquals(1, re.getX());
	}
	
	@Test
	public void testY() {
		//assertEquals(2, re.getY());
	}
	
	@Test 
	public void testType(){
		//assertEquals(SMOKING,re.getType());
	}

}
