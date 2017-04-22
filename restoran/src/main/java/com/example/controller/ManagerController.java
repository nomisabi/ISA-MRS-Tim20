package com.example.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Employee;
import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Supplier;
import com.example.domain.DTOs.GuestRegister;
import com.example.service.ManagerService;

@RestController
public class ManagerController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//@Autowired
	private ManagerService mService;
	
	@RequestMapping(
			value = "/api/manager/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manager> getManager(@PathVariable("id") Long id) {
		logger.info("> getManager id:{}", id);
		Manager manager = mService.findOne(id);
		if (manager == null) {
			return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);
		}
		logger.info("< getManager id:{}", id);
		return new ResponseEntity<Manager>(manager, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/api/manager/addManager", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manager> createManager(@Valid @RequestBody Manager man) throws Exception {
		logger.info("> addManager: "+man.getEmail()+","+ man.getFirstName()+","+ man.getRestaurant().toString());
		Manager m = mService.findByEmail(man.getEmail());
		if (m == null) {
			return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);
		}
		mService.createManager(m);
		logger.info("< addManager");		
		return new ResponseEntity<Manager>(m, HttpStatus.OK);
	}
	
	@PostMapping(value = "/createSupplier")
	public ModelAndView createSupplier(@Valid Supplier s, BindingResult result) throws Exception {
		logger.info("> createSupplier: ");
		if (result.hasErrors()) {
			return new ModelAndView("createManager", "formErrors", result.getAllErrors());
		}
		mService.createSupplier(s);
		logger.info("< createSupplier");
		
		return new ModelAndView("createManager", "manager", new Manager());
	}
	
	@PostMapping(value = "/createEmployee")
	public ModelAndView createEmployee(@Valid Employee e, BindingResult result) throws Exception {
		logger.info("> createEmployee: ");
		if (result.hasErrors()) {
			return new ModelAndView("createManager", "formErrors", result.getAllErrors());
		}
		mService.createEmployee(e);
		logger.info("< createEmployee");
		
		return new ModelAndView("createManager", "manager", new Manager());
	}
	
	@RequestMapping(
			value = "/api/manager/login", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manager> logIn(@Valid @RequestBody Manager man) throws Exception {
		logger.info("> logIn");
		System.out.println(man);
		Manager m = mService.findByEmail(man.getEmail());
		if (m != null){
			if (man.getPassword().equals(m.getPassword())){
				logger.info("success");
				return new ResponseEntity<Manager>(HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);	
	}
}
