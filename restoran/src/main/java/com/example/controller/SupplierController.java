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
import com.example.domain.Manager;
import com.example.domain.Supplier;
import com.example.domain.DTOs.GuestRegister;
import com.example.service.GuestService;
import com.example.service.SupplierService;

@RestController
public class SupplierController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SupplierService supService;

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
	public ResponseEntity<Supplier> logIn(@Valid @RequestBody Supplier s) throws Exception {
		logger.info("> logIn");
		System.out.println(s);
		Supplier supplier = supService.findByEmail(s.getEmail());
		if (supplier != null){
			if (s.getPassword().equals(supplier.getPassword())){		
				supService.setLogedIn(supplier);
				logger.info("success");
				return new ResponseEntity<Supplier>(s, HttpStatus.OK);
			}
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

			if (supService.changeData(sup)){
				logger.info("success");
				return new ResponseEntity<Supplier>(HttpStatus.OK);
			}
		return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);	
	}
	
	
	@RequestMapping(
			value = "/api/suppliers/getlogedin", 
			method = RequestMethod.GET,  
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> logIn() throws Exception {
		logger.info("> GetlogIn");
		Supplier s = supService.getLogedIn();
		if (s != null){
				return new ResponseEntity<Supplier>(s, HttpStatus.OK);
			}
		
		return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
	}

}
