package com.example.controller;

import java.util.Collection;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.hibernate.StaleObjectStateException;
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
import com.example.domain.Manager;
import com.example.domain.Offer;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.Supply;
import com.example.domain.User;
import com.example.domain.DTOs.GuestRegister;
import com.example.domain.DTOs.OfferSupply;
import com.example.service.GuestService;
import com.example.service.OfferSupplyService;
import com.example.service.SupplierService;
import com.example.service.UserService;

@RestController
public class SupplierController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SupplierService supService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OfferSupplyService osService;

	@RequestMapping(
			value = "/api/suppliers", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Supplier>> getSuppliers() {
		logger.info("> getSupplier");

		Collection<Supplier> suppliers = supService.findAll();
		if (suppliers.isEmpty()) {
			return new ResponseEntity<Collection<Supplier>>(HttpStatus.NO_CONTENT);
		}

		logger.info("< getSupplier");
		return new ResponseEntity<Collection<Supplier>>(suppliers, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/api/suppliers/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> getGuest(@PathVariable("id") Long id) {
		logger.info("> getSupplier id:{}", id);
		Supplier supplier = supService.findOne(id);
		if (supplier == null) {
			return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
		}
		logger.info("< getSupplier id:{}", id);
		return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
	}

	
	@RequestMapping(
			value = "/api/suppliers/login", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> logIn(@Valid @RequestBody  User u) throws Exception {
		logger.info("> logIn");
		Collection<Supplier> supp= supService.findAll();
		for (Supplier s:supp)
			if(u.getEmail().equals(s.getEmail())){
				logger.info("< logIn");
				return new ResponseEntity<Supplier>(s, HttpStatus.OK);		
		}
		return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);	
	}
	
	@RequestMapping(
			value = "/api/suppliers/changePass", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> changeP(@Valid @RequestBody Supplier sup) throws Exception {
		logger.info("> changePass");
		System.out.println(sup);
		sup.setActive(true);
		supService.update(sup);
		Collection<User> users= userService.findAll();
		for (User u:users){
			if (u.getEmail().equals(sup.getEmail())){
				User new_user= u;
				new_user.setPassword(sup.getPassword());
				userService.changePass(u, new_user);
			}		
		}
		logger.info("< changePass");
		return new ResponseEntity<Supplier>(HttpStatus.OK);	
	}
	

	@RequestMapping(
			value = "/api/suppliers/update", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> update(@Valid @RequestBody Supplier sup) throws Exception {
		logger.info("> update");
		Supplier old= supService.findOne(sup.getId());
		
		//ellenorizni h van-e vmelyik usernek -> ha van end
		Collection<User> users= userService.findAll();
		for (User u:users){
			if (u.getEmail().equals(sup.getEmail()) && !sup.getEmail().equals(old.getEmail())){
				return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		supService.update(sup);
		
		//update user if email changed
		if (!old.getEmail().equals(sup.getEmail()))
			for (User us:users){
				if (us.getEmail().equals(old.getEmail())){
					User new_user= us;
					new_user.setEmail(sup.getEmail());
					userService.changePass(us, new_user);
					userService.login(new_user);
				}		
			}
		return new ResponseEntity<Supplier>(HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/suppliers/restaurant", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Restaurant>> getRestaurants(@Valid @RequestBody  Supplier s) {
		logger.info("> getRestaurants: "+s.toString());

		Collection<Restaurant> restaurants = supService.getRest(s.getId());
		System.out.println("restaurants: "+restaurants.size());
		if (restaurants.isEmpty()) {
			return new ResponseEntity<Collection<Restaurant>>(HttpStatus.NO_CONTENT);
		}

		logger.info("< getRestaurants");
		return new ResponseEntity<Collection<Restaurant>>(restaurants, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/suppliers/sendOffer", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> sendOffer(@Valid @RequestBody  Offer o) {
		logger.info("> sendOffer");

		try{
			osService.updateOffer(o);
			if (o==null)
				return new ResponseEntity<Offer>(HttpStatus.NOT_FOUND);
			logger.info("< add Supply");
			return new ResponseEntity<Offer>(o,HttpStatus.OK);
		}		
		catch(Exception e) {
			return new ResponseEntity<Offer>(HttpStatus.I_AM_A_TEAPOT);
		}
	}
	
	@RequestMapping(
			value = "/api/suppliers/updateOffer", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> updateOffer(@Valid @RequestBody  OfferSupply os) {
		logger.info("> updateOffer: "+os.getS().toString());

		try{
			if (osService.updateOfferQualityAndPrice(os.getO(), os.getS())==null)
				return new ResponseEntity<Offer>(HttpStatus.NOT_FOUND);
			logger.info("< add Supply");
			return new ResponseEntity<Offer>(os.getO(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Offer>(HttpStatus.I_AM_A_TEAPOT);
		}
	}
	
	
	@RequestMapping(
			value = "/api/suppliers/getSuppliesWitMyOffer", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Supply>> getSupplyOffer(@Valid @RequestBody  Supplier s) {
		logger.info("> getSupplyOffer ");

		Collection<Supply> supp=osService.getSupplyWithMyOffer(s.getId());
		
		logger.info("< getSupplyOffer");
		return new ResponseEntity<Collection<Supply>>(supp,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/suppliers/getSuppliersRest", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Supplier>> getSuppliersRest(@Valid @RequestBody  Restaurant r) {
		logger.info("> getSuppliersRest ");

		Collection<Supplier> supp=supService.getSupp(r.getId());
		
		logger.info("< getSuppliersRest");
		return new ResponseEntity<Collection<Supplier>>(supp,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/suppliers/supply_not_choosed", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Supply>> getSupplyChoosed(@Valid @RequestBody Supplier s) throws Exception {
		logger.info("> get Supply");
		Collection<Supply> supply=(Collection<Supply>) osService.getNotChosedSupply(s.getId());
		if (supply.size()==0)
			return new ResponseEntity<Collection<Supply>>(HttpStatus.NOT_FOUND);
		logger.info("< get Supply");
		return new ResponseEntity<Collection<Supply>>(supply,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/suppliers/endOffer", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> endOffer(@Valid @RequestBody  Offer o) {
		logger.info("> endOffer");

		osService.updateOfferToEnd(o);
		
		logger.info("< endOffer");
		return new ResponseEntity<Offer>(o,HttpStatus.OK);
	}
	
}
