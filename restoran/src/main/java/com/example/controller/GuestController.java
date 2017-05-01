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
	

	@RequestMapping(
			value = "/api/guests", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> getGuests() {
		logger.info("> getGuests");
		Collection<Guest> guests = guestService.getAllGuests();
		for (Guest guest : guests) {
			System.out.println(guest);
		} 
		if (guests.isEmpty()) {
			return new ResponseEntity<Collection<Guest>>(HttpStatus.NO_CONTENT);
		}
		logger.info("< getGuests");
		return new ResponseEntity<Collection<Guest>>(HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/guestLog", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> getGuestLog() {
		logger.info("> getGuestLog");	
		Guest guest = (Guest) session.getAttribute("guest");
		if (guest== null) {
			return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
		}
		logger.info("< getGuestLog");
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/api/guests/{id}", 
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
			Guest savedGuest = guestService.addGuest(g);
			logger.info("< createGuest");
			return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
		}
		return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);	
	}
	
	/*
	@RequestMapping(
			value = "/api/guests/logIn", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> logIn(@Valid @RequestBody Guest guest) throws Exception {
		logger.info("> logIn");
		System.out.println(guest);
		Guest g = null; //guestService.findByEmail(guest.getEmail());
		if (g != null){
			if (guest.getPassword().equals(g.getPassword())){
				logger.info("success");
				session.setAttribute("guest", g);
				return new ResponseEntity<Guest>(g,HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);	
	}
	*/

    
    @RequestMapping(value = "/api/guests/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Guest> updateUser(@PathVariable("id") long id, @RequestBody Guest guest) throws Exception {
        System.out.println("Updating User " + id);
        System.out.println("Guest"+guest); 
        
        Guest currentGuest = guestService.getGuest(id);
        System.out.println("Current guest: "+currentGuest);
         
        if (currentGuest==null) {
            System.out.println("Guest with id " + id + " not found");
            return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
        }
        
        Guest updatedGuest = guestService.updateGuest(id, guest);
        session.setAttribute("guest", updatedGuest);
        System.out.println(updatedGuest);
        return new ResponseEntity<Guest>(updatedGuest,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/sendRequest", method = RequestMethod.POST)
    public ResponseEntity<Guest> sendRequest(@RequestBody FriendRequest friendRequest) throws Exception {
        System.out.println(friendRequest);
    	Guest currentGuest = null;//guestService.findOne(friendRequest.getIdGuest());
    	
    	if (currentGuest == null){
    		System.out.println("Guest with id " + friendRequest.getIdGuest() + " not found");  
    		return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
    	}
    	
    	Guest sessionGuest = (Guest) session.getAttribute("guest");
    	
    	if (currentGuest.getId() != sessionGuest.getId()){
    		System.out.println("Guest with id " + friendRequest.getIdGuest() + " not log in");  
    		return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
    	}
    	
    	
    	
    	
    	return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
    }
    
    

}
