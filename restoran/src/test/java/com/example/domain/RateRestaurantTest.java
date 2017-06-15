package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RateRestaurantTest {
	RateRestaurant rateRestaurant;
	Restaurant restaurant;
	Reservation reservation;
	Guest guest;
	SimpleDateFormat sdf;

	@Before
	public void setUp() {
		restaurant = new Restaurant("Restoran1", "proba");
		guest = new Guest("proba@proba.com", "proba12");
		sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		Date date = new Date();
		String startTime = sdf.format(date);
		date = new Date();
		String endTime = sdf.format(date);
		reservation = new Reservation(restaurant, startTime, endTime);
		rateRestaurant = new RateRestaurant(guest, restaurant, reservation, 5, 4);
		
	}
	
	@Test
	public void testGetReservation() {
		assertEquals(reservation, rateRestaurant.getReservation());
	}
	
	@Test
	public void testGetRestaurant() {
		assertEquals(restaurant, rateRestaurant.getRestaurant());
	}
	
	@Test
	public void testGetGuest() {
		assertEquals(guest, rateRestaurant.getGuest());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(5, rateRestaurant.getRate());
	}
	@Test
	public void testGetRateFood() {
		assertEquals(4, rateRestaurant.getRateFood());
	}
	
	@Test
	public void testSetReservation() {
		Reservation r = new Reservation();
		rateRestaurant.setReservation(r);
		assertEquals(r, rateRestaurant.getReservation());
	}
	
	@Test
	public void testSetRestaurant() {
		Restaurant r = new Restaurant();
		rateRestaurant.setRestaurant(r);
		assertEquals(r, rateRestaurant.getRestaurant());
	}
	
	@Test
	public void testSetGuest() {
		Guest g = new Guest();
		rateRestaurant.setGuest(g);
		assertEquals(g, rateRestaurant.getGuest());
	}
	
	@Test
	public void testSetRate() {
		rateRestaurant.setRate(4);
		assertEquals(4, rateRestaurant.getRate());
	}
	@Test
	public void testSetRateFood() {
		rateRestaurant.setRateFood(5);
		assertEquals(5, rateRestaurant.getRateFood());
	}
	
	@Test
	public void testRateNotNull() {
		assertNotNull(rateRestaurant);
	}
	

}
