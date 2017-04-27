package com.example.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.domain.Employee;

import com.example.domain.Manager;
import com.example.domain.Supplier;

import com.example.service.ManagerService;
import com.example.service.ManagerServiceImpl;

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
	
	@RequestMapping(value = "/api/manager/createSupplier", 
	method = RequestMethod.POST, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier>  createSupplier(@Valid @RequestBody Supplier s) throws Exception {
		logger.info("> createSupplier: ");

		mService.createSupplier(s);
		logger.info("< createSupplier");
		
		return new ResponseEntity<Supplier>(s, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/manager/createEmployee", 
	method = RequestMethod.POST, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee>  createEmployee(@Valid  @RequestBody Employee e) throws Exception {
		logger.info("> createEmployee: ");
			mService.createEmployee(e);
		logger.info("< createEmployee");
		
		return new ResponseEntity<Employee>(e, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/manager/login", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manager> logIn(@Valid @RequestBody Manager man) throws Exception {
		logger.info("> logIn");
		System.out.println(man);
		
		if (mService.findByEmail("email") != null){
			Manager m = mService.findByEmail(man.getEmail());
			if (man.getPassword().equals(m.getPassword())){
				logger.info("success");
				return new ResponseEntity<Manager>(m,HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);	
	}
	
	@RequestMapping(
			value = "/api/manager/changePass", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manager> changeP(@Valid @RequestBody Manager man, @Valid String newP, @Valid String oldP) throws Exception {
		logger.info("> logIn");
		System.out.println(man);

			if (mService.changePassword(newP, oldP, man)){
				logger.info("success");
				return new ResponseEntity<Manager>(HttpStatus.OK);
			}
		return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);	
	}
	
	
	@RequestMapping(
			value = "/api/manager/getlogedin", 
			method = RequestMethod.GET,  
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manager> logIn() throws Exception {
		logger.info("> GetlogIn");
		Manager m = mService.getLogedIn();
		if (m != null){
				return new ResponseEntity<Manager>(m, HttpStatus.OK);
			}
		
		return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);
	}
}
