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
import com.example.domain.Restaurant;
import com.example.domain.System_manager;
import com.example.service.EmployeeService;
import com.example.service.SystemManagerService;


@RestController
public class EmployeeContoller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	private EmployeeService emService;
	@Autowired
	private HttpSession session;


	@RequestMapping(
			value = "/api/employees", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> getEmployees() {
		logger.info("> getEmployees");

		Collection<Employee> employees = emService.findAll();
		if (employees.isEmpty()) {
			return new ResponseEntity<Collection<Employee>>(HttpStatus.NO_CONTENT);
		}

		logger.info("< getEmployees");
		return new ResponseEntity<Collection<Employee>>(employees, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/api/employeeLog", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeLog() {
		logger.info("> getEmployeeLog");	
		Employee employee = (Employee) session.getAttribute("employee");
		if (employee== null) {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
		logger.info("< getEmployeeLog");
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/api/employees/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		logger.info("> getEmployee id:{}", id);
		Employee employee = emService.findOne(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		logger.info("< getEmployee id:{}", id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/employees/login", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> logIn(@Valid @RequestBody Employee employee) throws Exception {
		logger.info("> logIn");
		System.out.println(employee);
		Employee employees = null;
		if (employees != null){
			if (employee.getPassword().equals(employees.getPassword())){
				logger.info("success");
				return new ResponseEntity<Employee>(HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);	
	}
	
	
	@RequestMapping(
			value = "/api/employees/changePass", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> changeP(@Valid @RequestBody Employee employee, @Valid String newP, @Valid String oldP) throws Exception {
		logger.info("> logIn");
		System.out.println(employee);

			//if (emService.changePassword(newP, oldP, employee)){
			//	logger.info("success");
				//return new ResponseEntity<Employee>(HttpStatus.OK);
			//}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);	
	}
	
	@RequestMapping(
			value = "/api/employees/getlogedin", 
			method = RequestMethod.GET,  
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> logIn() throws Exception {
		logger.info("> GetlogIn");
		Employee em =emService.getLogedIn();
		if (em != null){
				return new ResponseEntity<Employee>(em, HttpStatus.OK);
			}
		
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}

}
