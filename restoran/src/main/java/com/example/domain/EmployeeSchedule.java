package com.example.domain;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class EmployeeSchedule {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@Column
	private Date day;
	
	@Column
	private String start_time;
	@Column
	private String end_time;
	
	@Column
	private String c1;
	@Column
	private String c2;
	
	@OneToMany(cascade = { CascadeType.MERGE })
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

	public String getStart() {
		return start_time;
	}

	public void setStart(String start) {
		this.start_time = start;
	}

	public String getEnd() {
		return end_time;
	}

	public void setEnd(String end) {
		this.end_time = end;
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
		return "EmployeeSchedule [id=" + id + ", employee=" + employee + ", day=" + day + ", start=" + start_time + ", end="
				+ end_time + ", c1=" + c1 + ", c2=" + c2 + ", region=" + region + "]";
	}

	public EmployeeSchedule(long id, Employee employee, Date day, String start, String end, String c1, String c2,
			Set<Region> region) {
		super();
		this.id = id;
		this.employee = employee;
		this.day = day;
		this.start_time = start;
		this.end_time = end;
		this.c1 = c1;
		this.c2 = c2;
		this.region = region;
	}
	public EmployeeSchedule() {
		super();
		
	}
	
	
}
