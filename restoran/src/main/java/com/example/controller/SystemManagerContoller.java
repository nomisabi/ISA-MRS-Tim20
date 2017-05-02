package com.example.controller;

import java.util.Collection;

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

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;
import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.service.SystemManagerService;
import com.example.service.UserService;

@RestController
public class SystemManagerContoller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SystemManagerService smService;
	

	@RequestMapping(
			value = "/api/sysman", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<System_manager>> getSysMan() {
		logger.info("> getSysMan");

		/**/
		
		Collection<System_manager> sm = smService.findAll();
		if (sm.isEmpty()) {
			logger.info("< empyt");
			return new ResponseEntity<Collection<System_manager>>(HttpStatus.NO_CONTENT);
		}
		
		logger.info("< getSysMan");
		return new ResponseEntity<Collection<System_manager>>(sm, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/api/sysman/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<System_manager> getSystem_manager(@PathVariable("id") Long id) {
		logger.info("> getSystem_manager id:{}", id);
		System_manager s = smService.findOne(id);
		if (s == null) {
			return new ResponseEntity<System_manager>(HttpStatus.NOT_FOUND);
		}
		logger.info("< getSystem_manager id:{}", id);
		return new ResponseEntity<System_manager>(s, HttpStatus.OK);
	}
	

	@RequestMapping(
			value = "/api/sysman/signUp", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<System_manager>  signUp(@Valid @RequestBody System_manager sm) throws Exception {
		logger.info("> signUp: "+sm.getEmail()+", "+ sm.getPassword());

		//if (smService.signUP(sm)==null)
			//return new ResponseEntity<System_manager>(HttpStatus.NOT_FOUND);
		logger.info("< signUp");
		
		 return new ResponseEntity<System_manager>(HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/sysman/createManager", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manager> createManager(@Valid @RequestBody Manager m) throws Exception {
		logger.info("> createManager :"+m.toString());
		logger.info("< createManager");
		
		return new ResponseEntity<Manager>(m,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/sysman/login", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<System_manager> logIn(@Valid @RequestBody User u) throws Exception {
		logger.info("> logIn");
		
		Collection<System_manager> sm= smService.findAll();
		for (System_manager man:sm)
			if(u.getEmail().equals(man.getEmail()) && u.getPassword().equals(man.getPassword()))
				return new ResponseEntity<System_manager>(man, HttpStatus.OK);
		
		return new ResponseEntity<System_manager>(HttpStatus.NOT_FOUND);
	}
	/*
	@RequestMapping(
			value = "/api/sysman/getlogedin", 
			method = RequestMethod.GET,  
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<System_manager> logIn() throws Exception {
		logger.info("> GetlogIn");
		System_manager s = smService.getLogedIn();
		if (s != null){
				return new ResponseEntity<System_manager>(s, HttpStatus.OK);
			}
		
		return new ResponseEntity<System_manager>(HttpStatus.NOT_FOUND);
	}*/
	
	
}
