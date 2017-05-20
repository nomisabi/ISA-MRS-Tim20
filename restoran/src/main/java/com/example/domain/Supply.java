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
	private Date from_date;
	@Column(nullable = false)
	private Date to_date;
	@Column(nullable = false)
	private boolean chosed;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rest_id")
	private Restaurant restaurant;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="supply")
	private Set<Offer> offer;
	
	public Supply(long id, String name, Date from, Date to, boolean chosed) {
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

	public Date getFrom() {
		return from_date;
	}

	public void setFrom(Date from) {
		this.from_date = from;
	}

	public Date getTo() {
		return to_date;
	}

	public void setTo(Date to) {
		this.to_date = to;
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
	
	
}
