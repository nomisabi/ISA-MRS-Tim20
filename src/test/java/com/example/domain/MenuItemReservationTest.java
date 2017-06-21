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
	public void testIsPrepared() {
		assertEquals(false, menuItemReservation.isPrepared());
	}

	@Test
	public void testGetQuality() {
		assertEquals(0, menuItemReservation.getQuantity());
	}

	@Test
	public void testSetDrinkMenuItem() {
		MenuItem d = new MenuItem();
		menuItemReservation.setMenuItem(d);
		assertEquals(d, menuItemReservation.getMenuItem());
	}

	@Test
	public void testSetReservation() {
		Reservation r = new Reservation();
		menuItemReservation.setReservation(r);
		assertEquals(r, menuItemReservation.getReservation());
	}

	@Test
	public void testSetGuest() {
		Guest g = new Guest();
		menuItemReservation.setGuest(g);
		assertEquals(g, menuItemReservation.getGuest());
	}

	@Test
	public void testSetPrepared() {
		menuItemReservation.setPrepared(true);
		assertEquals(true, menuItemReservation.isPrepared());
	}

	@Test
	public void testSetQuality() {
		menuItemReservation.setQuantity(2);
		assertEquals(2, menuItemReservation.getQuantity());
	}

	@Test
	public void testNotNull() {
		assertNotNull(menuItemReservation);
	}

}
