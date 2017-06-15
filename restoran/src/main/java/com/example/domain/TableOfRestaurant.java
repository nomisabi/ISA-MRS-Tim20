package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TableOfRestaurant implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private int number;

	@Column
	private int numberOfChairs;

	@ManyToOne(fetch = FetchType.EAGER)
	private Restaurant restaurant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	private Region region;

	public TableOfRestaurant() {
	}

	public TableOfRestaurant(int number, int numberOfChairs, Restaurant restaurant) {
		super();
		this.number = number;
		this.numberOfChairs = numberOfChairs;
		this.restaurant = restaurant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumberOfChairs() {
		return numberOfChairs;
	}

	public void setNumberOfChairs(int numberOfChairs) {
		this.numberOfChairs = numberOfChairs;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/*
	 * public Region getRegion() { return region; }
	 * 
	 * public void setRegion(Region region) { this.region = region; }
	 */
	@Override
	public String toString() {
		return "TableOfRestaurant [number=" + number + ", numberOfChairs=" + numberOfChairs + ", id=" + id + "]";
	}

}