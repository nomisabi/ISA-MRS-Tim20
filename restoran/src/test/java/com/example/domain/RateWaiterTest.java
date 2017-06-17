package com.example.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RateWaiterTest {

	RateWaiter rw;
	Employee e;
	Restaurant r;
	
	@Before
	public void setUp(){
		e= new Employee();
		r= new Restaurant();
		rw= new RateWaiter((long) 1, e, r, 4);
	}
	
	@Test
	public void TestId() {
		Long id=(long) 1;
		assertEquals(id, rw.getId());
	}

	@Test
	public void TestEmployee() {
		assertEquals(e, rw.getEmployee());
	}
	
	@Test
	public void TestRest() {
		assertEquals(r, rw.getRestaurant());
	}
	
	@Test
	public void TestRate() {
		assertEquals(4, rw.getRate());
	}
	


}
