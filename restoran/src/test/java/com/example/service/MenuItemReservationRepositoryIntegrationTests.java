package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Guest;
import com.example.domain.MenuItem;
import com.example.domain.MenuItemReservation;
import com.example.domain.Reservation;
import com.example.domain.Restaurant;
import com.example.respository.GuestRepository;
import com.example.respository.MenuItemRepository;
import com.example.respository.MenuItemReservationRepository;
import com.example.respository.ReservationRepository;
import com.example.respository.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuItemReservationRepositoryIntegrationTests {
	@Autowired
	GuestRepository guestRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	MenuItemRepository menuItemRepository;
	@Autowired
	MenuItemReservationRepository menuItemReservationRepository;

	Guest guest;
	Reservation reservation;
	MenuItem menuItem;
	MenuItemReservation menuItemReservation;

	@Before
	public void setUp() {
		guest = new Guest("novi@gmail.com", "123456", "proba", "proba1");
		guest = guestRepository.save(guest);
		Restaurant restaurant = new Restaurant("proba", "proba");
		restaurant = restaurantRepository.save(restaurant);
		reservation = new Reservation(restaurant, "13-05-2017 15:00", "13-05-2017 17:00");
		reservation = reservationRepository.save(reservation);
		menuItem = new MenuItem();
		menuItem = menuItemRepository.save(menuItem);
		menuItemReservation = new MenuItemReservation(menuItem, guest, reservation);
		menuItemReservation = menuItemReservationRepository.save(menuItemReservation);
	}

	@Test
	public void getMenuItemReservation() {
		MenuItemReservation find = menuItemReservationRepository.findOne(menuItemReservation.getId());
		assertNotNull(find);
	}
	
	@Test
	public void getMenuItems() {
		Collection<MenuItemReservation> find = menuItemReservationRepository.getMenuItems(reservation.getId(), guest.getId());
		assertNotEquals(0, find.size());
	}
	
	@Test
	public void update() {
		menuItemReservationRepository.update(menuItemReservation.getId(), 7);
		MenuItemReservation find = menuItemReservationRepository.findOne(menuItemReservation.getId());
		assertEquals(7, find.getQuantity());
	}
	
	

}
