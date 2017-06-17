package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SupplyTest {
	
	Supply sup;
	Restaurant r;
	Set<Offer> o;
	
	@Before
	public void setUp(){
		sup = new Supply("name", "from", "to", true);
		sup.setId((long)1);
		r= new Restaurant();
		sup.setRestaurant(r);
		o= new HashSet<Offer>();
		sup.setOffer(o);
	}
	
	@Test
	public void testSupplyName(){
		assertEquals("name", sup.getName());
	}
	
	@Test
	public void testSupplyFrom(){
		assertEquals("from", sup.getFrom_date());
	}
	@Test
	public void testSupplyTo(){
		assertEquals("to", sup.getTo_date());
	}
	
	@Test
	public void testSupplyChosen(){
		assertEquals(true, sup.isChosed());
	}
	
	@Test
	public void testSupplyRest(){
		assertEquals(r, sup.getRestaurant());
	}
	
	@Test
	public void testSupplyOffer(){
		assertEquals(o, sup.getOffer());
	}

	

}
