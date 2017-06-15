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
public class ReservationTest {
	Reservation reservation;
	Restaurant restaurant;
	Guest guest;
	String startTime;
	String endTime;
	SimpleDateFormat sdf;

	@Before
	public void setUp() {
		restaurant = new Restaurant("Restoran1", "proba");
		guest = new Guest("proba@proba.com", "proba12");
		sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		Date date = new Date();
		startTime = sdf.format(date);
		date = new Date();
		endTime = sdf.format(date);
		reservation = new Reservation(restaurant, startTime, endTime);

	}

	@Test
	public void testGetRestaurant() {
		assertEquals(restaurant, reservation.getRestaurant());
	}

	@Test
	public void testGetStartTime() {
		assertEquals(startTime, reservation.getStartTime());
	}

	@Test
	public void testGetEndTime() {
		assertEquals(endTime, reservation.getEndTime());
	}
	
	@Test
	public void testIsRate() {
		assertEquals(false, reservation.isRate());
	}

	@Test
	public void testSetRestaurant() {
		Restaurant r = new Restaurant();
		reservation.setRestaurant(r);
		assertEquals(r, reservation.getRestaurant());
	}

	@Test
	public void testSetStartTime() {
		Date date = new Date();
		startTime = sdf.format(date);
		reservation.setStartTime(startTime);
		assertEquals(startTime, reservation.getStartTime());
	}

	@Test
	public void testSetEndTime() {
		Date date = new Date();
		endTime = sdf.format(date);
		reservation.setEndTime(endTime);
		assertEquals(endTime, reservation.getEndTime());
	}
	
	@Test
	public void testSetRate() {
		reservation.setRate(true);
		assertEquals(true, reservation.isRate());
	}

	@Test
	public void testReservationNotNull() {
		assertNotNull(reservation);
	}

}
