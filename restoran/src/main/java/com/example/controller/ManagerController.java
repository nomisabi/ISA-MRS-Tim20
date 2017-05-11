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

import com.example.domain.Employee;
import com.example.domain.Food;
import com.example.domain.Manager;
import com.example.domain.Menu;
import com.example.domain.MenuItem;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.domain.DTOs.EmployeeRestaurant;
import com.example.domain.DTOs.SupplierRestaurant;
import com.example.service.ManagerService;
import com.example.service.MenuService;
import com.example.service.RestaurantService;
import com.example.service.SystemManagerService;
import com.example.service.UserService;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
	@Autowired
	private SystemManagerService smService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RestaurantService restService;
	
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
	
	@RequestMapping(
			value = "/api/manager/getRestColl", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Manager>> getRstColl(@Valid @RequestBody Collection<Manager> managers) throws Exception {
		logger.info("> getRest");
		for (Manager m: managers){
			Restaurant r= mService.findRest(m.getId());
			if (r!=null){
				m.setRestaurant(r);}
		}		
		return new ResponseEntity<Collection<Manager>>(managers, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/api/manager/createSupplier", 
	method = RequestMethod.POST, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier>  createSupplier(@Valid @RequestBody SupplierRestaurant sr) throws Exception {
		logger.info("> createSupplier: ");
		Collection<User> users = userService.findAll();
		for (User u: users)
			if (u.getEmail().equals(sr.getS().getEmail()))
				return new ResponseEntity<Supplier>( HttpStatus.NOT_FOUND);
		
		User user = new User(sr.getS().getEmail(), sr.getS().getPassword(), TypeOfUser.SUPPLIER);
		Set<Restaurant> rest = new HashSet<Restaurant>();
		rest.add(sr.getR());
		//sr.getS().setRestaurants(rest);
		Supplier s =mService.createSupplier(sr.getS());
		Set<Supplier> supp = sr.getR().getSuppliers();
		supp.add(s);
		sr.getR().setSuppliers(supp);
		userService.addUser(user);
		//smService.update(sr.getR());
		mService.updateRest(sr.getR().getId(), sr.getS().getId());
		logger.info("< createSupplier");
		
		return new ResponseEntity<Supplier>(sr.getS(), HttpStatus.OK);
	}
	
	//email ellenorzes
	//beirni userbe
	@RequestMapping(value = "/api/manager/createEmployee", 
	method = RequestMethod.POST, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee>  createEmployee(@Valid  @RequestBody EmployeeRestaurant er) throws Exception {
		logger.info("> createEmployee: "+er.getE().getId());
		Collection<User> users = userService.findAll();
		for (User u: users)
			if (u.getEmail().equals(er.getE().getEmail()))
				return new ResponseEntity<Employee>( HttpStatus.NOT_FOUND);
		
		User user = new User(er.getE().getEmail(), er.getE().getPassword(), TypeOfUser.EMPLOYEE);
		Employee e =mService.createEmployee(er.getE());
		Set<Employee> empl = er.getR().getEmployee();
		empl.add(e);
		er.getR().setEmployee(empl);
		userService.addUser(user);
		smService.update(er.getR());
		
		logger.info("< createEmployee");	
		return new ResponseEntity<Employee>(er.getE(), HttpStatus.OK);
	
	}
	
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
	
	
	@RequestMapping(
			value = "/api/manager/update", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manager> update(@Valid @RequestBody Manager man) throws Exception {
		logger.info("> update");
		Manager old= mService.findOne(man.getId());
		
		//ellenorizni h van-e vmelyik usernek -> ha van end
		Collection<User> users= userService.findAll();
		for (User u:users){
			if (u.getEmail().equals(man.getEmail()) && !man.getEmail().equals(old.getEmail())){
				return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		mService.update(man);
		
		//update user if email changed
		if (!old.getEmail().equals(man.getEmail()))
			for (User us:users){
				if (us.getEmail().equals(old.getEmail())){
					User new_user= us;
					new_user.setEmail(man.getEmail());
					userService.changePass(us, new_user);
					userService.login(new_user);
				}		
			}
		return new ResponseEntity<Manager>(HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/manager/addMenuItem", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Menu> update(@Valid @RequestBody MenuRestaurant rm) throws Exception {
		Menu m= rm.getM();
		logger.info("> add menu item: "+m.toString());
		Collection<MenuItem> items= m.getItems();
		for (MenuItem mi: items){
			if (!menuService.isMenuItemExist(mi.getId())){
				Food f= mi.getFood();
				menuService.createFood(f);
				menuService.createMenuItem(mi);
			}
		}
		if (rm.getR().getMenu()==null){
			m= menuService.createMenu(m);
			rm.getR().setMenu(m);
			restService.updateMenu(rm.getR());
		}
		else
		{
			menuService.insertNewItem(m);
		}
		logger.info("< add menu item");
		return new ResponseEntity<Menu>(m,HttpStatus.OK);
	}
	
	
	
}
