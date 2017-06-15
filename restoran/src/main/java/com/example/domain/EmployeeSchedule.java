package com.example.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class EmployeeSchedule implements Serializable{

	private static final long serialVersionUID =  1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@Column
	private Date day;
	
	@Column
	private String startTime;
	@Column
	private String endTime;
	
	@Column
	private String c1;
	@Column
	private String c2;
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy= "schedule")
	private Set<Region> region;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String start_time) {
		this.startTime = start_time;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String end_time) {
		this.endTime = end_time;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public Set<Region> getRegion() {
		return region;
	}

	public void setRegion(Set<Region> region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "EmployeeSchedule [id=" + id + ", employee=" + employee + ", day=" + day + ", start=" + startTime + ", end="
				+ endTime + ", c1=" + c1 + ", c2=" + c2 + ", region=" + region + "]";
	}

	public EmployeeSchedule(long id, Employee employee, Date day, String startTime, String endTime, String c1, String c2,
			Set<Region> region) {
		super();
		this.id = id;
		this.employee = employee;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.c1 = c1;
		this.c2 = c2;
		this.region = region;
	}
	public EmployeeSchedule() {
		super();
		
	}
	
	
}
