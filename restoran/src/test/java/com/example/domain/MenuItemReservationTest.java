package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MenuItemReservationTest {

	MenuItemReservation menuItemReservation;
	Guest guest;
	Reservation reservation;
	MenuItem menuItem;

	@Before
	public void setUp() {
		guest = new Guest("proba@proba.com", "proba", "Proba", "ProbaLastName");
		Restaurant restaurant = new Restaurant("proba", "proba");
		reservation = new Reservation(restaurant, "13-05-2017 15:00", "13-05-2017 16:00");
		menuItem = new MenuItem();
		menuItemReservation = new MenuItemReservation(menuItem, guest, reservation);
	}

	@Test
	public void testGetMenuItem() {
		assertEquals(menuItem, menuItemReservation.getMenuItem());
	}

	@Test
	public void testGetReservation() {
		assertEquals(reservation, menuItemReservation.getReservation());
	}

	@Test
	public void testGetGuest() {
		assertEquals(guest, menuItemReservation.getGuest());
	}

	@Test
	public void testNotNull() {
		assertNotNull(menuItemReservation);
	}

}
