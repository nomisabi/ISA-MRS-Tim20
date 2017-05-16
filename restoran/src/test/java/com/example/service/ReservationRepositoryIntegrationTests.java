package com.example.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Reservation;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;
import com.example.respository.GuestRepository;
import com.example.respository.ReservationRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.TableOfRestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryIntegrationTests {

	@Autowired
	ReservationRepository repository;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	TableOfRestaurantRepository tableRepository;
	@Autowired
	GuestRepository guestRepository;

	Reservation reservation;

	@Before
	public void setUp() {
		Restaurant restaurant = new Restaurant("proba", "proba");
		restaurant = restaurantRepository.save(restaurant);
		TableOfRestaurant table = new TableOfRestaurant(1, 5, restaurant);
		table = tableRepository.save(table);
		reservation = new Reservation(restaurant, "2017-05-08 15:00", "2017-05-08 17:00");
		reservation = repository.save(reservation);
	}

	@Test
	public void getReservation() {
		Reservation findReservation = repository.findOne(reservation.getId());
		assertNotNull(findReservation);
	}

}
