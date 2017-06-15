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
public class RateMenuItemTest {
	RateMenuItem rateMenuItem;
	Reservation reservation;
	Guest guest;
	MenuItem menuItem;
	SimpleDateFormat sdf;

	@Before
	public void setUp() {
		Restaurant restaurant = new Restaurant("Restoran1", "proba");
		guest = new Guest("proba@proba.com", "proba12");
		sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		Date date = new Date();
		String startTime = sdf.format(date);
		date = new Date();
		String endTime = sdf.format(date);
		reservation = new Reservation(restaurant, startTime, endTime);
		menuItem = new MenuItem();
		rateMenuItem = new RateMenuItem(guest, menuItem, reservation, 5);
	}
	
	@Test
	public void testGetReservation() {
		assertEquals(reservation, rateMenuItem.getReservation());
	}
	
	@Test
	public void testGetGuest() {
		assertEquals(guest, rateMenuItem.getGuest());
	}
	
	@Test
	public void testGetMenuItem() {
		assertEquals(menuItem, rateMenuItem.getMenuItem());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(5, rateMenuItem.getRate());
	}
	
	@Test
	public void testSetReservation() {
		Reservation r = new Reservation();
		rateMenuItem.setReservation(r);
		assertEquals(r, rateMenuItem.getReservation());
	}
	
	@Test
	public void testSetGuest() {
		Guest g = new Guest();
		rateMenuItem.setGuest(g);
		assertEquals(g, rateMenuItem.getGuest());
	}
	
	@Test
	public void testSetMenuItem() {
		MenuItem m = new MenuItem();
		rateMenuItem.setMenuItem(m);
		assertEquals(m, rateMenuItem.getMenuItem());
	}
	
	@Test
	public void testSetRate() {
		rateMenuItem.setRate(4);
		assertEquals(4, rateMenuItem.getRate());
	}
	@Test
	public void testRateNotNull() {
		assertNotNull(rateMenuItem);
	}

}
