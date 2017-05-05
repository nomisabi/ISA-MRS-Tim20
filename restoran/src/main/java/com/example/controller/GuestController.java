package com.example.controller;

import java.util.Collection;

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
import com.example.domain.User;
import com.example.domain.DTOs.FriendRequest;
import com.example.domain.DTOs.GuestRegister;
import com.example.service.GuestService;

@RestController
public class GuestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GuestService guestService;
	@Autowired
	private HttpSession session;

	/*** Get all guests ***/
	@RequestMapping(value = "/api/guests", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> getGuests() {
		logger.info("> getGuests");
		Collection<Guest> guests = guestService.getAllGuests();
		logger.info("< getGuests");
		return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
	}

	/*** Guest log in ***/
	@RequestMapping(value = "/api/guest/login", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
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
	@RequestMapping(value = "/api/guests/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
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
	@RequestMapping(value = "/api/guests", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
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
	@RequestMapping(value = "/api/guests/{id}", 
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
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
	@RequestMapping(value = "/api/friendship/sendRequest",
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	/*** Return all guest with firstName or lastName that match the search criteria***/
	@RequestMapping(value = "/api/friendship/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> searchGuests(@RequestBody Guest guest) {
		logger.info("> getGuests");
		System.out.println(guest);
		Guest cuurrentGuest = (Guest) session.getAttribute("guest");
		System.out.println(cuurrentGuest);
		Collection<Guest> guests = guestService.searchGuest(cuurrentGuest.getId(), guest.getFirstName());

		return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
	}

	/*
	@RequestMapping(value = "/api/friendship{id}", method = RequestMethod.POST)
	public ResponseEntity<Guest> addFriend(@PathVariable("id") long id, @RequestBody FriendRequest friendRequest)
			throws Exception {
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

		guestService.addFriend(id, currentGuest, friendGuest);

		return new ResponseEntity<Guest>(currentGuest, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/deleteFriend", method = RequestMethod.POST)
	public ResponseEntity<Guest> deleteFriend(@RequestBody FriendRequest friendRequest) throws Exception {
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

		Guest savedGuest = guestService.deleteFriend(currentGuest, friendGuest);

		return new ResponseEntity<Guest>(savedGuest, HttpStatus.OK);
	}
	*/

	

}
