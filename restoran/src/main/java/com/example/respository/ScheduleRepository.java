package com.example.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.EmployeeSchedule;
import com.example.domain.Schedule;

@Repository
public interface ScheduleRepository  extends CrudRepository<Schedule, Long>{

}
