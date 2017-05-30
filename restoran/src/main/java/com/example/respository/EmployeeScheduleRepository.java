package com.example.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.EmployeeSchedule;
import com.example.domain.Manager;

@Repository
public interface EmployeeScheduleRepository extends CrudRepository<EmployeeSchedule, Long>{

}
