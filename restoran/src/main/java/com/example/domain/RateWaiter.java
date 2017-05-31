package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RateWaiter implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private Employee employee;
	@ManyToOne(fetch = FetchType.EAGER)
	private Restaurant restaurant;
	@Column
	private int rate;
	
	public RateWaiter(Long id, Employee employee, Restaurant restaurant, int rate) {
		super();
		this.id = id;
		this.employee = employee;
		this.restaurant = restaurant;
		this.rate = rate;
	}
	
	
}
