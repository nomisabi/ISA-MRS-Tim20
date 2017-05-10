package com.example.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Employee;
import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;
import com.example.respository.EmployeeRepository;
import com.example.respository.SystemManagerRepository;

@Service
public class EmployeeServiceImp implements EmployeeService{
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private EmployeeRepository emRepository;
    
	@Override
	public Boolean login(Employee e) {
		
		logger.info("> login");
		//Boolean value= emRepository.login(e);
        logger.info("< login");
		return true;
	}

	@Override
	public Collection<Employee> findAll() {
		logger.info("> findAll");
		Collection<Employee> employees = emRepository.findAll();
		logger.info("< findAll");
		return employees;
	}

	@Override
	public Employee findOne(Long id) {
		logger.info("> findOne id:{}", id);
		Employee employee = emRepository.findOne(id);
		logger.info("< findOne id:{}", id);
		return employee;
	}
	
	@Override
	public Employee findByEmail(String email){
		logger.info("> findByEmail email:{}", email);
		//Employee em = emRepository.findByEmail(email);
		logger.info("< findByEmail email:{}", email);
		return null;
		
	}

	@Override
	public boolean isEmployeeExist(Employee em){
		//return emRepository.isEmployeeExist(em);
		return true;
	}
    
	@Override
	public boolean changePassword(String newP, String oldP, Employee em) {
		logger.info("> changePass ", em);
		//boolean val = emRepository.changePassword(newP, oldP, em);
		logger.info("< changePass :{}", em);
		return true;
	}
	
	@Override
	public Employee getLogedIn() {
		//return emRepository.getLogedIn();
		return null;
	}

	@Override
	public void setLogedIn(Employee em) {
	//	emRepository.setLogedIn( em);
		
	}

    
}
