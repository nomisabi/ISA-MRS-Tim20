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
import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.service.SystemManagerService;
import com.example.service.UserService;

@RestController
public class UserContoller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private User login=null;
	@Autowired
	private UserService userService;
	@Autowired
	private SystemManagerService smService;
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
			value = "/api/users/sysman", 
			method = RequestMethod.GET)
	public void savesysman() {
		logger.info("> save");
		Collection<System_manager> managers= smService.findAll();
		for (System_manager sman:managers){
			if (sman.getEmail().equals("admin@admin.com"))
				return;
		}
		
		System_manager sm= new System_manager("admin@admin.com", "admin","admin","admin");
		smService.addSysMan(sm);
		User u = new User("admin@admin.com", "admin",TypeOfUser.SYS_MAN);
		userService.addUser(u);
		
		logger.info("< save");
	}

	
	@RequestMapping(
			value = "/api/users/login", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> logIn(@Valid @RequestBody User u) throws Exception {
		logger.info("> logIn");
		System.out.println(u);

		Collection<User> users = userService.findAll();
		if (!users.isEmpty()){
			for (User user : users) {
		        if (user.getEmail().equals(u.getEmail()) && user.getPassword().equals(u.getPassword())){
		        	logger.info("< logIn");
		        	login=user;
		        	return new ResponseEntity<User>(user, HttpStatus.OK);
		    	}
			}	
		}
		logger.info("< logIn - error");
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			value = "/api/users/login", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getLogin() {
		logger.info("> login");

		if (login==null) {
			logger.info("< empyt");
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}

		logger.info("< login");
		return new ResponseEntity<User>(login, HttpStatus.OK);
	}
	

}
