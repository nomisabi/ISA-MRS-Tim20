package com.example.domain;


import java.io.Serializable;
import java.util.HashMap;
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

public class Region implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private int x;
	@Column
	private int y;
	//@OneToMany
	private Set<TableOfRestaurant> tables;
	@Column 
	private TypeRegion type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;
	
	public Region(){
		
	}
	
	public Region(int x, int y, TypeRegion type, Restaurant restaurant){
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		this.restaurant = restaurant;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Set<TableOfRestaurant> getTables() {
		return tables;
	}

	public void setTables(Set<TableOfRestaurant> tables) {
		this.tables = tables;
	}

	public TypeRegion getType() {
		return type;
	}

	public void setType(TypeRegion type) {
		this.type = type;
	}
	
	

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", x=" + x + ", y=" + y + ", tables=" + tables + ", type=" + type + ", restaurant="
				+ restaurant + "]";
	}

	
	
}
