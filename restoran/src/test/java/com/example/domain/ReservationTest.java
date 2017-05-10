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
	TableOfRestaurant table;
	Guest guest;
	String startTime;
	String endTime;

	@Before
	public void setUp() {
		restaurant = new Restaurant("Restoran1", "proba");
		table = new TableOfRestaurant(1, 1, restaurant);
		guest = new Guest("proba@proba.com", "proba12");
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		Date date = new Date();
		startTime = sdf.format(date);
		date = new Date();
		endTime = sdf.format(date);
		reservation = new Reservation(restaurant, table, guest, startTime, endTime);

	}

	@Test
	public void testGetRestaurant() {
		assertEquals(restaurant, reservation.getRestaurant());
	}

	@Test
	public void testGetTable() {
		assertEquals(table, reservation.getTable());
	}

	@Test
	public void testGetGuest() {
		assertEquals(guest, reservation.getGuest());
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
	public void testReservationNotNull() {
		assertNotNull(reservation);
	}

}
