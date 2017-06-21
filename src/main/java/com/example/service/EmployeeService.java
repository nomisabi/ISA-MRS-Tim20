package com.example.service;

import java.util.Collection;

import com.example.domain.Employee;
import com.example.domain.Manager;

public interface EmployeeService {
	
	Boolean login(Employee e);
	
	Collection<Employee> findAll();

	Employee findOne(Long id);

	Employee getLogedIn();
	void setLogedIn(Employee em);

}
