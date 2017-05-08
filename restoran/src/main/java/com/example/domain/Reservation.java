package com.example.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	private Restaurant restaurant;

	@ManyToOne(optional = false)
	private TableOfRestaurant table;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guest_id", nullable = false)
	private Guest guest;

	// @Temporal(TemporalType.TIMESTAMP )
	// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Transient
	private Date dateAndTime;
	@Column
	private String startTime;
	@Column
	private String endTime;

	@Transient
	private int duration;

	public Reservation() {
	}

	public Reservation(Restaurant restaurant, TableOfRestaurant table, Guest guest, String startTime, String endTime) {
		super();
		this.restaurant = restaurant;
		this.table = table;
		this.guest = guest;
		this.startTime = startTime;
		this.endTime = endTime;
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

	public TableOfRestaurant getTable() {
		return table;
	}

	public void setTable(TableOfRestaurant table) {
		this.table = table;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Reservation [restaurant=" + restaurant + ", table=" + table + ", guest=" + guest + ", dateAndTime="
				+ dateAndTime + ", startTime=" + startTime + ", endTime=" + endTime + ", duration=" + duration + "]";
	}

}
