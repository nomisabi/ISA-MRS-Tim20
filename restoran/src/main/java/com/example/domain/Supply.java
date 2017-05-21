package com.example.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;

@Entity
public class Supply implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String from_date;
	@Column(nullable = false)
	private String to_date;
	@Column(nullable = false)
	private boolean chosed;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rest_id")
	private Restaurant restaurant;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="supply")
	private Set<Offer> offer;
	
	public Supply(long id, String name, String from, String to, boolean chosed) {
		super();
		this.id = id;
		this.name = name;
		this.from_date = from;
		this.to_date = to;
		this.chosed = chosed;
	}
	
	public Supply() {
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public boolean isChosed() {
		return chosed;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Set<Offer> getOffer() {
		return offer;
	}

	public void setOffer(Set<Offer> offer) {
		this.offer = offer;
	}

	public void setChosed(boolean chosed) {
		this.chosed = chosed;
	}

	@Override
	public String toString() {
		return "Supply [id=" + id + ", name=" + name + ", from_date=" + from_date + ", to_date=" + to_date + ", chosed="
				+ chosed + ", restaurant=" + restaurant + ", offer=" + offer + "]";
	}
	
	
}
