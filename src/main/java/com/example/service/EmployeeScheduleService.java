package com.example.service;

import java.util.Collection;

import com.example.domain.EmployeeSchedule;

public interface EmployeeScheduleService {

	Collection<EmployeeSchedule> findAll();

	EmployeeSchedule findOne(Long id);

	boolean isEmployeeScheduleExist(Long id);
	
	EmployeeSchedule addEmployeeSchedule(EmployeeSchedule es);
	
	EmployeeSchedule updateEmployeeSchedule(EmployeeSchedule es);
	
	void deleteEmployeeSchedule(Long id);
}
