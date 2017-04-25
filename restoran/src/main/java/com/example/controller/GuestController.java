package com.example.controller;

import java.util.Collection;

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
import com.example.domain.DTOs.GuestRegister;
import com.example.service.GuestService;

@RestController
public class GuestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GuestService guestService;

	@RequestMapping(
			value = "/api/guests", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> getGuests() {
		logger.info("> getGuests");

		Collection<Guest> guests = guestService.findAll();
		if (guests.isEmpty()) {
			return new ResponseEntity<Collection<Guest>>(HttpStatus.NO_CONTENT);
		}

		logger.info("< getGuests");
		return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/api/guests/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> getGuest(@PathVariable("id") Long id) {
		logger.info("> getGuest id:{}", id);
		Guest guest = guestService.findOne(id);
		if (guest == null) {
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		logger.info("< getGuest id:{}", id);
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/api/guests", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> createGuest(@Valid @RequestBody GuestRegister guest) throws Exception {
		logger.info("> createGuest");
		System.out.println(guest);
		if (guest.getPassword().equals(guest.getPassword2())) {
			Guest g = new Guest(guest.getEmail(), guest.getPassword());
			Guest savedGuest = guestService.createGuest(g);
			logger.info("< createGuest");
			return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
		}
		return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);	
	}
	
	@RequestMapping(
			value = "/api/guests/logIn", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> logIn(@Valid @RequestBody Guest guest) throws Exception {
		logger.info("> logIn");
		System.out.println(guest);
		Guest g = guestService.findByEmail(guest.getEmail());
		if (g != null){
			if (guest.getPassword().equals(g.getPassword())){
				logger.info("success");
				return new ResponseEntity<Guest>(HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);	
	}

    
    @RequestMapping(value = "/api/guests/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Guest> updateUser(@PathVariable("id") long id, @RequestBody Guest guest) throws Exception {
        System.out.println("Updating User " + id);
         
        Guest currentGuest = guestService.findOne(id);
         
        if (currentGuest==null) {
            System.out.println("Guest with id " + id + " not found");
            return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
        }
         
        Guest updatedGuest = guestService.update(currentGuest);
        return new ResponseEntity<Guest>(updatedGuest, HttpStatus.OK);
    }

}
