package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Guest;
import com.example.domain.GuestReservation;
import com.example.domain.RateRestaurant;
import com.example.domain.Region;
import com.example.domain.Reservation;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;
import com.example.domain.TableReservation;
import com.example.domain.VerificationToken;
import com.example.respository.GuestReservationRepository;
import com.example.respository.VerificationTokenRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceIntegrationTests {
	
	@Autowired
	ReservationService service;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	TableOfRestaurantService tableOfRestaurantService;
	@Autowired
	RegionService regionService;
	@Autowired
	GuestService guestService;
	@Autowired
	GuestReservationRepository guestReservationRepository;
	@Autowired
	VerificationTokenRepository tokenRepository;
	
	Reservation reservation;
	Restaurant restaurant;
	Guest guest;
	Guest friend;
	GuestReservation guestReservation;
	GuestReservation guestReservationFriend;
	TableReservation tableReservation;
	

	@Before
	public void setUp() {
		restaurant = new Restaurant("proba", "proba");
		restaurant = restaurantService.createRestaurant(restaurant);
		
		Region region = new Region(restaurant);
		region = regionService.addRegion(region);
		TableOfRestaurant table = new TableOfRestaurant(1, 5, restaurant);
		table = tableOfRestaurantService.addTable(table, region.getId());
		
		reservation = new Reservation(restaurant, "2017-05-08 15:00", "2017-05-08 17:00");
		reservation = service.createReservation(reservation);
		
		tableReservation = new TableReservation(table, reservation, reservation.getStartTime(), reservation.getEndTime());
		tableReservation = service.saveTable(tableReservation);
		
		guest = new Guest("novi@gmail.com", "123456", "proba", "proba1");
		guest = guestService.addGuest(guest);
		
		friend = new Guest("novi2@gmail.com", "1234562", "proba2", "proba2");
		friend = guestService.addGuest(guest);
		
		guestReservation = new GuestReservation(guest, reservation);
		guestReservation = service.saveGuestReservation(guestReservation);
		
		guestReservationFriend = new GuestReservation(guest, reservation, false);
		guestReservationFriend = service.saveGuestReservation(guestReservation);
		
		VerificationToken v = new VerificationToken(guestReservationFriend, UUID.randomUUID().toString());
		tokenRepository.save(v);
		
	}
	
	@Test
	public void get() {
		Reservation findReservation = service.getReservation(reservation.getId());
		assertNotNull(findReservation);
	}
	
	@Test
	public void getTable() {
		Collection<TableOfRestaurant> t = service.getAllTableOfReservation(reservation.getId());
		assertEquals(1, t.size());
	}
	
	@Test
	public void getGuest() {
		Collection<Guest> g = service.getFriends(reservation.getId(), guest.getId());
		assertEquals(0, g.size());
	}
	
	@Test
	public void confirmReservation() {
		assertEquals(false, guestReservationFriend.isAccepted());
		service.confirmReservation(guestReservationFriend.getId());
		GuestReservation g = guestReservationRepository.findOne(guestReservationFriend.getId());
		assertEquals(true, g.isAccepted());
		Long idToken = service.getVerificationId(g.getId());
		assertNotNull(idToken);
		VerificationToken t = tokenRepository.findOne(idToken);
		assertNotNull(t);
		service.deleteToken(idToken);
		boolean exist = tokenRepository.exists(idToken);
		assertEquals(false, exist);	
	}
	
	@Test
	public void saveRateRestaurant() {
		RateRestaurant rateRestaurant = new RateRestaurant(guest, restaurant, reservation, 5, 5);
		service.saveRateRestaurant(rateRestaurant);
		RateRestaurant r = service.getrate(reservation.getId(), guest.getId());
		
		assertEquals(rateRestaurant.getRate(), r.getRate());
		assertEquals(rateRestaurant.getRateFood(), r.getRateFood());
		
	}

}
