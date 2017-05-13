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
public class TableReservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private TableOfRestaurant table;
	@ManyToOne(fetch = FetchType.EAGER)
	private Reservation reservation;
	@Column
	private String startTime;
	@Column
	private String endTime;

	public TableReservation() {
	}

	public TableReservation(TableOfRestaurant table, Reservation reservation, String startTime, String endTime) {
		super();
		this.table = table;
		this.reservation = reservation;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public TableReservation(TableOfRestaurant table, String startTime, String endTime) {
		super();
		this.table = table;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TableOfRestaurant getTable() {
		return table;
	}

	public void setTable(TableOfRestaurant table) {
		this.table = table;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
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
		return "TableReservation [table=" + table + ", reservation=" + reservation + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}

}
