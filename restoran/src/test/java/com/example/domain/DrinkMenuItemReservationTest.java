package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DrinkMenuItemReservationTest {

	DrinkMenuItemReservation drinkMenuItemReservation;
	Guest guest;
	Reservation reservation;
	DrinkMenuItem drinkMenuItem;

	@Before
	public void setUp() {
		guest = new Guest("proba@proba.com", "proba", "Proba", "ProbaLastName");
		Restaurant restaurant = new Restaurant("proba", "proba");
		reservation = new Reservation(restaurant, "13-05-2017 15:00", "13-05-2017 16:00");
		drinkMenuItem = new DrinkMenuItem();
		drinkMenuItemReservation = new DrinkMenuItemReservation(drinkMenuItem, guest, reservation);
	}

	@Test
	public void testGetDrinkMenuItem() {
		assertEquals(drinkMenuItem, drinkMenuItemReservation.getDrinkMenuItem());
	}

	@Test
	public void testGetReservation() {
		assertEquals(reservation, drinkMenuItemReservation.getReservation());
	}

	@Test
	public void testGetGuest() {
		assertEquals(guest, drinkMenuItemReservation.getGuest());
	}
	@Test
	public void testIsPrepared() {
		assertEquals(false, drinkMenuItemReservation.isPrepared());
	}
	
	@Test
	public void testGetQuality() {
		assertEquals(0, drinkMenuItemReservation.getQuantity());
	}
	
	@Test
	public void testSetDrinkMenuItem() {
		DrinkMenuItem d = new DrinkMenuItem();
		drinkMenuItemReservation.setDrinkMenuItem(d);
		assertEquals(d, drinkMenuItemReservation.getDrinkMenuItem());
	}

	@Test
	public void testSetReservation() {
		Reservation r = new Reservation();
		drinkMenuItemReservation.setReservation(r);
		assertEquals(r, drinkMenuItemReservation.getReservation());
	}

	@Test
	public void testSetGuest() {
		Guest g = new Guest();
		drinkMenuItemReservation.setGuest(g);
		assertEquals(g, drinkMenuItemReservation.getGuest());
	}
	@Test
	public void testSetPrepared() {
		drinkMenuItemReservation.setPrepared(true);
		assertEquals(true, drinkMenuItemReservation.isPrepared());
	}
	
	@Test
	public void testSetQuality() {
		drinkMenuItemReservation.setQuantity(2);
		assertEquals(2, drinkMenuItemReservation.getQuantity());
	}


	@Test
	public void testNotNull() {
		assertNotNull(drinkMenuItemReservation);
	}

}
