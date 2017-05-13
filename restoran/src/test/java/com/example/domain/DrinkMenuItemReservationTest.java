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
	public void testNotNull() {
		assertNotNull(drinkMenuItemReservation);
	}

}
