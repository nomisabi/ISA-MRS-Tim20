package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.EmployeeSchedule;
import com.example.respository.EmployeeScheduleRepository;

@Service
public class EmployeeScheduleServiceImpl implements EmployeeScheduleService{

    @Autowired
    private EmployeeScheduleRepository esRepository;
    
    
	@Override
	public Collection<EmployeeSchedule> findAll() {
		return (Collection<EmployeeSchedule>) esRepository.findAll();
	}

	@Override
	public EmployeeSchedule findOne(Long id) {
		return esRepository.findOne(id);
	}

	@Override
	public boolean isEmployeeScheduleExist(Long id) {
		return esRepository.exists(id);
	}

	@Override
	public EmployeeSchedule addEmployeeSchedule(EmployeeSchedule es) {
		EmployeeSchedule e= esRepository.save(es);
		return e;
	}

	@Override
	public void deleteEmployeeSchedule(Long id) {
		esRepository.delete(id);
		
	}

}
