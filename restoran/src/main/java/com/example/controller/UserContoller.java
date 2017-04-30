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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.System_manager;
import com.example.domain.User;
import com.example.service.UserService;

@RestController
public class UserContoller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	
	@RequestMapping(
			value = "/api/users", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getSysMan() {
		logger.info("> getSUser");

		Collection<User> users = userService.findAll();

		if (users.isEmpty()) {
			logger.info("< empyt");
			return new ResponseEntity<Collection<User>>(HttpStatus.NO_CONTENT);
		}

		logger.info("< getSysMan");
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

	
	@RequestMapping(
			value = "/api/users/login", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> logIn(@Valid @RequestBody User u) throws Exception {
		logger.info("> logIn");
		System.out.println(u);
		User user = userService.findByEmail(u.getEmail());		
		if (user != null){			
			if (u.getPassword().equals(user.getPassword())){
				logger.info("success");	
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		}	
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	

}
