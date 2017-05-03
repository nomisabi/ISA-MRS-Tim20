package com.example.service;

import java.util.Collection;

import com.example.domain.Employee;
import com.example.domain.Manager;

public interface EmployeeService {
	
	Boolean login(Employee e);
	
	Collection<Employee> findAll();

	Employee findOne(Long id);

	Employee findByEmail(String email);

	boolean isEmployeeExist(Employee em);
	
	boolean changePassword(String newP, String oldP, Employee m);

	Employee getLogedIn();
	void setLogedIn(Employee em);

}
