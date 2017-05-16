package com.example.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Region implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="region")
	private Set<TableOfRestaurant> tables;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;

	public Region() {

	}

	public Region( Restaurant restaurant) {
		super();
		this.restaurant = restaurant;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Set<TableOfRestaurant> getTables() {
		return tables;
	}

	public void setTables(Set<TableOfRestaurant> tables) {
		this.tables = tables;
	}

	@Override
	public String toString() {
		return "Region [id=" + id +  ", tables=" + tables +", name="+name+ ", restaurant="
				+ restaurant + "]";
	}

}