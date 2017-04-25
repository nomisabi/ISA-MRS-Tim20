package com.example.service;

import java.util.Collection;

import com.example.domain.Employee;

public interface EmployeeService {
	
	Boolean login(Employee e);
	
	Collection<Employee> findAll();

	Employee findOne(Long id);

	Employee findByEmail(String email);

	boolean isEmployeeExist(Employee em);

}
