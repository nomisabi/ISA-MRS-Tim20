package com.example.domain.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Employee;
import com.example.domain.Offer;
import com.example.domain.Restaurant;
import com.example.domain.Supply;
import com.example.domain.DTOs.EmployeeRestaurant;
import com.example.domain.DTOs.OfferSupply;

@RunWith(SpringRunner.class)
public class OfferSupplyTest {
	OfferSupply os;
	Offer o;
	Supply s;

	@Before
	public void setUp(){
		o= new Offer();
		s= new Supply();
		os= new OfferSupply(o, s);
	}
	
	@Test
	public void TestOffer() {
		assertEquals(o, os.getO());
	}
	
	@Test
	public void TestSupply() {
		assertEquals(s, os.getS());
	}
	
	@Test
	public void TestOfferSupply() {
		assertNotNull(os);
	}

}
