package com.example.domain.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Offer;
import com.example.domain.Supply;
import com.example.domain.DTOs.OfferSupply;
import com.example.domain.DTOs.RateWaiter;

@RunWith(SpringRunner.class)
public class RateWaiterTest {

	RateWaiter rw;
	

	@Before
	public void setUp(){
		double rate= 4.4;
		rw= new RateWaiter("firstName", "lastName", rate);
	}
	
	@Test
	public void TestFirstName() {
		assertEquals("firstName", rw.getFirstName());
	}
	
	@Test
	public void TestLastName() {
		assertEquals("lastName", rw.getLastName());
	}
	
	private static final double DELTA = 1e-15;
	@Test
	public void TestPrice() {
		assertEquals(4.4, rw.getRate(), DELTA);
	}
	
	@Test
	public void TestRateWaiter() {
		assertNotNull(rw);
	}

}
