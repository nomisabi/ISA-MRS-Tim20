package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Guest;
import com.example.domain.Reservation;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;
import com.example.domain.User;
import com.example.domain.DTOs.FriendRequest;
import com.example.domain.DTOs.GuestRegister;
import com.example.domain.DTOs.Table;
import com.example.service.GuestService;
import com.example.service.ReservationService;
import com.example.service.RestaurantService;
import com.example.service.TableOfRestaurantService;

@RestController
public class GuestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GuestService guestService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private TableOfRestaurantService tableService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private HttpSession session;

	/*** Get all guests ***/
	@RequestMapping(value = "/api/guests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> getGuests() {
		logger.info("> getGuests");
		Collection<Guest> guests = guestService.getAllGuests();
		logger.info("< getGuests");
		return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
	}

	/*** Guest log in ***/
	@RequestMapping(value = "/api/guest/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> getGuestLog(@Valid @RequestBody User user) {
		logger.info("> Guest log in");
		System.out.println(user);
		if (user == null) {
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}

		Guest guest = guestService.findByEmailAndPass(user.getEmail(), user.getPassword());
		System.out.println("Guest from base: " + guest);
		session.setAttribute("guest", guest);

		logger.info("< Guest log in");
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}

	/*** Get guest with {id} ***/
	@RequestMapping(value = "/api/guests/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> getGuest(@PathVariable("id") Long id) {
		logger.info("> getGuest id:{}", id);
		Guest guest = guestService.getGuest(id);
		if (guest == null) {
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		logger.info("< getGuest id:{}", id);
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}

	/*** Guest registration ***/
	@RequestMapping(value = "/api/guests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> createGuest(@Valid @RequestBody GuestRegister guest) throws Exception {
		logger.info("> createGuest");
		System.out.println(guest);
		if (guest.getPassword().equals(guest.getPassword2())) {
			Guest g = new Guest(guest.getEmail(), guest.getPassword(), guest.getFirstName(), guest.getLastName());
			Guest savedGuest = guestService.addGuest(g);
			logger.info("< createGuest");
			return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
		}
		return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
	}

	/*** Guest update ***/
	@RequestMapping(value = "/api/guests/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> updateUser(@PathVariable("id") long id, @RequestBody Guest guest) throws Exception {
		System.out.println("Updating User " + id);
		System.out.println("Guest" + guest);

		Guest currentGuest = guestService.getGuest(id);
		System.out.println("Current guest: " + currentGuest);

		if (currentGuest == null) {
			System.out.println("Guest with id " + id + " not found");
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}

		Guest updatedGuest = guestService.updateGuest(id, guest);
		session.setAttribute("guest", updatedGuest);
		System.out.println("Updating guest" + updatedGuest);
		return new ResponseEntity<Guest>(updatedGuest, HttpStatus.OK);
	}

	/*** Send friendship request ***/
	@RequestMapping(value = "/api/friendship/sendRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> sendRequest(@RequestBody FriendRequest friendRequest) throws Exception {
		System.out.println(friendRequest);
		Guest currentGuest = guestService.getGuest(friendRequest.getIdGuest());

		if (currentGuest == null) {
			System.out.println("Guest with id " + friendRequest.getIdGuest() + " not found");
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}

		Guest sessionGuest = (Guest) session.getAttribute("guest");

		if (currentGuest.getId() != sessionGuest.getId()) {
			System.out.println("Guest with id " + friendRequest.getIdGuest() + " not log in");
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}

		Guest friendGuest = guestService.getGuest(friendRequest.getIdFriend());

		if (friendGuest == null) {
			System.out.println("Guest with id " + friendRequest.getIdGuest() + " not found");
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}

		guestService.sendFriendRequest(currentGuest, friendGuest);

		return new ResponseEntity<Guest>(currentGuest, HttpStatus.OK);
	}

	/***
	 * Return all guest with firstName or lastName that match the search
	 * criteria
	 ***/
	@RequestMapping(value = "/api/friendship/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> searchGuests(@RequestBody Guest guest) {
		logger.info("> getGuests");
		System.out.println(guest);
		Guest cuurrentGuest = (Guest) session.getAttribute("guest");
		System.out.println(cuurrentGuest);
		Collection<Guest> guests = guestService.searchGuest(cuurrentGuest.getId(), guest.getFirstName());

		return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
	}

	/*** Get friends ***/
	@RequestMapping(value = "/api/friends", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> getFriends(@RequestBody Guest guest) {
		logger.info("> getFriends");
		Guest currentGuest = (Guest) session.getAttribute("guest");

		if (currentGuest == null) {
			System.out.println("Guest with id " + guest.getId() + " not found");
			return new ResponseEntity<Collection<Guest>>(HttpStatus.NOT_FOUND);
		}

		Collection<Guest> friends = guestService.findFriends(currentGuest.getId());
		logger.info("< getFriends");
		return new ResponseEntity<Collection<Guest>>(friends, HttpStatus.OK);
	}

	/*** Get all restaurants ***/
	@RequestMapping(value = "/api/restaurants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Restaurant>> getRestaurants() {
		logger.info("> getRestaurants");
		Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
		logger.info("< getRestaurants");
		return new ResponseEntity<Collection<Restaurant>>(restaurants, HttpStatus.OK);
	}

	/*** Return all tables ***/
	@RequestMapping(value = "/api/restaurant/tables", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Table>> getAvaliableTables(@RequestBody Reservation reservation) {
		logger.info("> getAvaliableTables");
		System.out.println(reservation);
		System.out.println(reservation.getRestaurant().getId());
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		reservation.setStartTime(sdf.format(reservation.getDateAndTime()));
		Date endDate = reservation.getDateAndTime();
		endDate.setHours(endDate.getHours() + reservation.getDuration());
		reservation.setEndTime(sdf.format(endDate));

		Collection<Reservation> reservations = reservationService.getAllReservationOfRestaurantInTime(
				reservation.getRestaurant().getId(), reservation.getStartTime(), reservation.getEndTime());

		HashMap<Long, Table> tableSS = new HashMap<Long, Table>();
		for (Reservation reservation2 : reservations) {
			System.out.println(reservation2.getTable());
			tableSS.put(reservation2.getTable().getId(), new Table(reservation2.getTable(), true));

		}

		Collection<TableOfRestaurant> tables = tableService
				.getAllTableOfRestaurant(reservation.getRestaurant().getId());
		for (TableOfRestaurant tableOfRestaurant : tables) {
			System.out.println(tableOfRestaurant);
			if (!tableSS.containsKey(tableOfRestaurant.getId())) {
				tableSS.put(tableOfRestaurant.getId(), new Table(tableOfRestaurant, false));
			}
		}

		for (Table tab : tableSS.values()) {
			System.out.println(tab);
		}

		return new ResponseEntity<Collection<Table>>(tableSS.values(), HttpStatus.OK);
	}

	/*** Return all tables ***/
	@RequestMapping(value = "/api/restaurant/reservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> makeReservation(@RequestBody Reservation reservation) {
		logger.info("> makeReservation");
		System.out.println(reservation);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		reservation.setStartTime(sdf.format(reservation.getDateAndTime()));
		Date endTime = reservation.getDateAndTime();
		endTime.setHours(endTime.getHours() + reservation.getDuration());
		System.out.println(endTime);
		reservation.setEndTime(sdf.format(endTime));

		Reservation savedReservation = reservationService.createReservation(reservation);
		System.out.println(savedReservation);
		return new ResponseEntity<Reservation>(HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/api/friendship{id}", method =
	 * RequestMethod.POST) public ResponseEntity<Guest>
	 * addFriend(@PathVariable("id") long id, @RequestBody FriendRequest
	 * friendRequest) throws Exception { System.out.println(friendRequest);
	 * Guest currentGuest = guestService.getGuest(friendRequest.getIdGuest());
	 * 
	 * if (currentGuest == null) { System.out.println("Guest with id " +
	 * friendRequest.getIdGuest() + " not found"); return new
	 * ResponseEntity<Guest>(HttpStatus.NOT_FOUND); }
	 * 
	 * Guest sessionGuest = (Guest) session.getAttribute("guest");
	 * 
	 * if (currentGuest.getId() != sessionGuest.getId()) {
	 * System.out.println("Guest with id " + friendRequest.getIdGuest() +
	 * " not log in"); return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND); }
	 * 
	 * Guest friendGuest = guestService.getGuest(friendRequest.getIdFriend());
	 * 
	 * if (friendGuest == null) { System.out.println("Guest with id " +
	 * friendRequest.getIdGuest() + " not found"); return new
	 * ResponseEntity<Guest>(HttpStatus.NOT_FOUND); }
	 * 
	 * guestService.addFriend(id, currentGuest, friendGuest);
	 * 
	 * return new ResponseEntity<Guest>(currentGuest, HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/api/deleteFriend", method = RequestMethod.POST)
	 * public ResponseEntity<Guest> deleteFriend(@RequestBody FriendRequest
	 * friendRequest) throws Exception { System.out.println(friendRequest);
	 * Guest currentGuest = guestService.getGuest(friendRequest.getIdGuest());
	 * 
	 * if (currentGuest == null) { System.out.println("Guest with id " +
	 * friendRequest.getIdGuest() + " not found"); return new
	 * ResponseEntity<Guest>(HttpStatus.NOT_FOUND); }
	 * 
	 * Guest sessionGuest = (Guest) session.getAttribute("guest");
	 * 
	 * if (currentGuest.getId() != sessionGuest.getId()) {
	 * System.out.println("Guest with id " + friendRequest.getIdGuest() +
	 * " not log in"); return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND); }
	 * 
	 * Guest friendGuest = guestService.getGuest(friendRequest.getIdFriend());
	 * 
	 * if (friendGuest == null) { System.out.println("Guest with id " +
	 * friendRequest.getIdGuest() + " not found"); return new
	 * ResponseEntity<Guest>(HttpStatus.NOT_FOUND); }
	 * 
	 * Guest savedGuest = guestService.deleteFriend(currentGuest, friendGuest);
	 * 
	 * return new ResponseEntity<Guest>(savedGuest, HttpStatus.OK); }
	 */

}
