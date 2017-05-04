package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.service.ManagerService;
import com.example.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.Collection;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class ManagerController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ManagerService mService;
	@Autowired
	private UserService userService;
	
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


	@RequestMapping(
			value = "/api/manager", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Manager>> getMan() {
		logger.info("> getSysMan");

		/**/
		
		Collection<Manager> sm = mService.findAll();
		if (sm.isEmpty()) {
			logger.info("< empyt");
			return new ResponseEntity<Collection<Manager>>(HttpStatus.NO_CONTENT);
		}
	
		logger.info("< getSysMan");
		return new ResponseEntity<Collection<Manager>>(sm, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/manager/addManager", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manager> createManager(@Valid @RequestBody Manager man) throws Exception {
		logger.info("> addManager");
		Collection<User> users = userService.findAll();
		if (!users.isEmpty()) {
			for (User u:users){
				if (u.getEmail().equals(man.getEmail()))
					return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);
			}	
		}
		Manager manager = mService.createManager(man);
		User u= new User(manager.getEmail(), manager.getPassword(), TypeOfUser.MANAGER);
		userService.addUser(u);
		logger.info("< addManager");		
		return new ResponseEntity<Manager>(manager, HttpStatus.OK);
	}

	
	@RequestMapping(
			value = "/api/manager/login", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manager> logIn(@Valid @RequestBody User u) throws Exception {
		logger.info("> logIn");
		
		Collection<Manager> sm= mService.findAll();
		for (Manager man:sm)
			if(u.getEmail().equals(man.getEmail())){
				logger.info("< logIn");
				return new ResponseEntity<Manager>(man, HttpStatus.OK);
			}
		return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(
			value = "/api/manager/getRest", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restaurant> getRst(@Valid @RequestBody Manager m) throws Exception {
		logger.info("> getRest");
		
		Restaurant r= mService.findRest(m.getId());
		if (r!=null){
			m.setRestaurant(r);
			return new ResponseEntity<Restaurant>(r, HttpStatus.OK);
		}
		return new ResponseEntity<Restaurant>(HttpStatus.NOT_FOUND);
	}
	
/*	@RequestMapping(value = "/api/manager/createSupplier", 
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
		Manager m = mService.findByEmail(man.getEmail());
		if (m != null){		
			if (man.getPassword().equals(m.getPassword())){
				logger.info("success");
				return new ResponseEntity<Manager>(m,HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);	
	}*/
	
	@RequestMapping(
			value = "/api/manager/changePass", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manager> changeP(@Valid @RequestBody Manager man) throws Exception {
		logger.info("> logIn");
		System.out.println(man);
		man.setActive(true);
		mService.update(man);
		Collection<User> users= userService.findAll();
		for (User u:users){
			if (u.getEmail().equals(man.getEmail())){
				User new_user= u;
				new_user.setPassword(man.getPassword());
				userService.changePass(u, new_user);
			}		
		}
		return new ResponseEntity<Manager>(HttpStatus.OK);
	}
	
	/*
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

*/
	
	
}
