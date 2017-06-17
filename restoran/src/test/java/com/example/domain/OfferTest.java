package com.example.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class OfferTest {

	Offer o;
	Supplier s;
	
	@Before
	public void setUp(){
		o= new Offer(1, 5.5, Offer_status.CHOOSED);
		s= new Supplier();
		o.setId((long)1);
		o.setSupplier(s);
	}
	
	@Test
	public void TestId() {
		assertEquals((long) 1, o.getId());
	}

	@Test
	public void TestPrice() {
		assertEquals(5,5 , o.getPrice());
	}
	
	@Test
	public void TestQuantity() {
		assertEquals(1, o.getQuality());
	}
	
	@Test
	public void TestStatus() {
		assertEquals(Offer_status.CHOOSED, o.getStatus());
	}
	
	@Test
	public void TestSupplier() {
		assertEquals(s, o.getSupplier());
	}


}
